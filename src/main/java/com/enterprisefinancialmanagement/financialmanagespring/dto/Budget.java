package com.enterprisefinancialmanagement.financialmanagespring.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Budget {

    private int budgetId;
    private String budgetName;
    private String description;
    private String category;
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;

    public int getBudgetId() { return budgetId; }
    public void setBudgetId(int budgetId) { this.budgetId = budgetId; }

    public String getBudgetName() { return budgetName; }
    public void setBudgetName(String budgetName) { this.budgetName = budgetName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}

