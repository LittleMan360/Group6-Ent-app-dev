package com.enterprisefinancialmanagement.financialmanagespring.dao;
import com.enterprisefinancialmanagement.financialmanagespring.domain.Txn;
import java.time.LocalDate;
import java.util.*;

public interface TxnDao {
    Txn save(Txn txn);
    Optional<Txn> findById(UUID id);
    List<Txn> findByUser(UUID userId);
    List<Txn> findByUserAndDateRange(UUID userId, LocalDate from, LocalDate to);
    void delete(UUID id);
}

