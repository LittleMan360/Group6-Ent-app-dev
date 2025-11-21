package com.enterprisefinancialmanagement.financialmanagespring.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Budget {

    private int budgetId;
    private String budgetName;
    private String description;

    // Category of the budget (e.g. Groceries, Rent, Utilities)
    private String category;

    // Total amount allocated for this budget
    private BigDecimal amount;

    private LocalDate startDate;
    private LocalDate endDate;
}

