package com.enterprisefinancialmanagement.financialmanagespring.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object for Budget
 */
@Data
public class Budget {
    private int budgetId;
    private BigDecimal amount;
    private String category;
    private String budgetName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
