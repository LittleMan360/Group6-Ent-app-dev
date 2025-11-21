package com.enterprisefinancialmanagement.financialmanagespring.service.impl;

import com.enterprisefinancialmanagement.financialmanagespring.dao.UserDao;
import com.enterprisefinancialmanagement.financialmanagespring.domain.User;
import com.enterprisefinancialmanagement.financialmanagespring.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao users;

    public UserServiceImpl(UserDao users) {
        this.users = users;
    }

    @Override
    @Transactional
    public User register(String email, String displayName) {
        users.findByEmail(email).ifPresent(u -> {
            throw new IllegalArgumentException("Email already registered");
        });
        User u = new User();
        u.setEmail(email);
        u.setDisplayName(displayName);
        return users.save(u);
    }

    @Override public Optional<User> getByEmail(String email) { return users.findByEmail(email); }
    @Override public Optional<User> getById(UUID id) { return users.findById(id); }
    @Override public List<User> list() { return users.findAll(); }
}
