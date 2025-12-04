package com.enterprisefinancialmanagement.financialmanagespring.dto;

import java.math.BigDecimal;

public class MonthlySpendingRow {

    private String category;
    private BigDecimal budget;
    private BigDecimal spent;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getSpent() {
        return spent;
    }

    public void setSpent(BigDecimal spent) {
        this.spent = spent;
    }
}
