package com.enterprisefinancialmanagement.financialmanagespring.dto;

import java.math.BigDecimal;

public class CategorySummary {

    private String category;
    private BigDecimal budget = BigDecimal.ZERO;
    private BigDecimal spent = BigDecimal.ZERO;
    private BigDecimal remaining = BigDecimal.ZERO;
    private BigDecimal percentOfTotal = BigDecimal.ZERO;

    public CategorySummary() {}

    public CategorySummary(String category) {
        this.category = category;
    }

    // getters & setters

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

    public BigDecimal getRemaining() {
        return remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }

    public BigDecimal getPercentOfTotal() {
        return percentOfTotal;
    }

    public void setPercentOfTotal(BigDecimal percentOfTotal) {
        this.percentOfTotal = percentOfTotal;
    }
}
