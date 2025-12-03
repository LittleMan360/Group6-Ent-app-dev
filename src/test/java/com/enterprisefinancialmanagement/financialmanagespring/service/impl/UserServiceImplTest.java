package com.enterprisefinancialmanagement.financialmanagespring.service.impl;

import com.enterprisefinancialmanagement.financialmanagespring.dao.UserDao;
import com.enterprisefinancialmanagement.financialmanagespring.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void registerSuccess() {
        doReturn(Optional.empty()).when(userDao).findByEmail("alice@example.com");
        doReturn(new User() {{ setId(UUID.randomUUID()); setEmail("alice@example.com"); setDisplayName("Alice"); }}).when(userDao).save(any());

        User created = userService.register("alice@example.com", "Alice");

        assertNotNull(created);
        assertEquals("alice@example.com", created.getEmail());
        assertEquals("Alice", created.getDisplayName());
        assertNotNull(created.getId());
    }

    @Test
    void registerDuplicateEmailThrows() {
        User existing = new User();
        existing.setId(UUID.randomUUID());
        existing.setEmail("bob@example.com");
        doReturn(Optional.of(existing)).when(userDao).findByEmail("bob@example.com");

        assertThrows(IllegalArgumentException.class, () -> userService.register("bob@example.com", "Bob"));
    }
}
