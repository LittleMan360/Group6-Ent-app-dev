package com.enterprisefinancialmanagement.financialmanagespring.config;

import com.enterprisefinancialmanagement.financialmanagespring.dao.UserDao;
import com.enterprisefinancialmanagement.financialmanagespring.domain.User;
import com.enterprisefinancialmanagement.financialmanagespring.service.TxnService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
@Profile("stub")
public class StubConfig {

    @Bean
    CommandLineRunner seed(UserDao users, TxnService txns) {
        return args -> {
            User india = new User();
            india.setEmail("india@example.com");
            india.setDisplayName("India");
            india = users.save(india);

            txns.addIncome(india.getId(), new BigDecimal("2500"), "Paycheck",
                    LocalDate.now().minusDays(5), "First check");
            txns.addExpense(india.getId(), new BigDecimal("120.50"), "Groceries",
                    LocalDate.now().minusDays(4), "Kroger");
        };
    }
}

