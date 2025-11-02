package com.enterprisefinancialmanagement.financialmanagespring;

import com.enterprisefinancialmanagement.financialmanagespring.dao.IBudgetRepository;
import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabaseSeeder {
    private final IBudgetRepository budgetRepository;

    public DatabaseSeeder(IBudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> {
            if (budgetRepository.count() == 0) { // only seed if empty
                Budget budget1 = new Budget();
                budget1.setBudgetName("Marketing Budget");
                budget1.setDescription("Q4 marketing plan");
                budget1.setStartDate(LocalDate.of(2025, 11, 1));
                budget1.setEndDate(LocalDate.of(2025, 12, 31));

                Budget budget2 = new Budget();
                budget2.setBudgetName("R&D Budget");
                budget2.setDescription("2025 R&D initiatives");
                budget2.setStartDate(LocalDate.of(2025, 1, 1));
                budget2.setEndDate(LocalDate.of(2025, 12, 31));

                budgetRepository.save(budget1);
                budgetRepository.save(budget2);

                System.out.println("Seeded budgets");
            }
        };
    }


}
