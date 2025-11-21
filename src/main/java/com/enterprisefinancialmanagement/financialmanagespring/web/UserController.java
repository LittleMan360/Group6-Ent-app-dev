package com.enterprisefinancialmanagement.financialmanagespring.web;

import com.enterprisefinancialmanagement.financialmanagespring.domain.User;
import com.enterprisefinancialmanagement.financialmanagespring.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService users;

    public UserController(UserService users) {
        this.users = users;
    }

    @PostMapping("/register")
    public User register(@RequestParam String email, @RequestParam String displayName) {
        return users.register(email, displayName);
    }

    @GetMapping
    public List<User> list() {
        return users.list();
    }
}

