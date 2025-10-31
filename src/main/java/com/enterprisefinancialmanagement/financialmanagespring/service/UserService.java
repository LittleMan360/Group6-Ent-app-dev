package com.enterprisefinancialmanagement.financialmanagespring.service;

import com.enterprisefinancialmanagement.financialmanagespring.domain.User;
import java.util.*;

public interface UserService {
    User register(String email, String displayName);
    Optional<User> getByEmail(String email);
    Optional<User> getById(UUID id);
    List<User> list();
}
