package com.enterprisefinancialmanagement.financialmanagespring.dao;
import com.enterprisefinancialmanagement.financialmanagespring.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("stub")
public class InMemoryUserDao implements UserDao {

    private final Map<UUID, User> store = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {
        if (user.getId() == null) user.setId(UUID.randomUUID());
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return store.values()
                .stream()
                .filter(u -> email != null && email.equalsIgnoreCase(u.getEmail()))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }
}

