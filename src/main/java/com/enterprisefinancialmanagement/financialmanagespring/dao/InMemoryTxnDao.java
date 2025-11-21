package com.enterprisefinancialmanagement.financialmanagespring.dao;
import com.enterprisefinancialmanagement.financialmanagespring.domain.Txn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("stub")
public class InMemoryTxnDao implements TxnDao {

    private final Map<UUID, Txn> store = new ConcurrentHashMap<>();

    @Override
    public Txn save(Txn txn) {
        if (txn.getId() == null) txn.setId(UUID.randomUUID());
        store.put(txn.getId(), txn);
        return txn;
    }

    @Override
    public Optional<Txn> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Txn> findByUser(UUID userId) {
        return store.values().stream()
                .filter(t -> userId.equals(t.getUserId()))
                .sorted(Comparator.comparing(Txn::getDate, Comparator.nullsLast(Comparator.naturalOrder())))
                .toList();
    }

    @Override
    public List<Txn> findByUserAndDateRange(UUID userId, LocalDate from, LocalDate to) {
        return store.values().stream()
                .filter(t -> userId.equals(t.getUserId()))
                .filter(t -> {
                    LocalDate d = t.getDate();
                    return d != null && !d.isBefore(from) && !d.isAfter(to);
                })
                .sorted(Comparator.comparing(Txn::getDate))
                .toList();
    }

    @Override
    public void delete(UUID id) {
        store.remove(id);
    }
}
