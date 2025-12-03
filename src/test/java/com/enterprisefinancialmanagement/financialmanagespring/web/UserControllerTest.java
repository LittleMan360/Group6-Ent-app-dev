package com.enterprisefinancialmanagement.financialmanagespring.web;

import com.enterprisefinancialmanagement.financialmanagespring.domain.User;
import com.enterprisefinancialmanagement.financialmanagespring.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Test
    void registerReturnsCreatedUser() {
        UserService users = mock(UserService.class);
        UserController controller = new UserController(users);

        User u = new User();
        u.setId(UUID.randomUUID());
        u.setEmail("test@example.com");
        u.setDisplayName("Test");

        when(users.register("test@example.com", "Test")).thenReturn(u);

        User result = controller.register("test@example.com", "Test");

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("test@example.com");
        verify(users).register("test@example.com", "Test");
    }

    @Test
    void listReturnsUsers() {
        UserService users = mock(UserService.class);
        UserController controller = new UserController(users);

        User a = new User(); a.setId(UUID.randomUUID()); a.setEmail("a@x.com");
        when(users.list()).thenReturn(List.of(a));

        List<User> result = controller.list();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEmail()).isEqualTo("a@x.com");
        verify(users).list();
    }
}
