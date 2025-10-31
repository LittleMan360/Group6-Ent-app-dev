package com.enterprisefinancialmanagement.financialmanagespring.dao;
import com.enterprisefinancialmanagement.financialmanagespring.domain.User;
import java.util.*;

public interface UserDao {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
