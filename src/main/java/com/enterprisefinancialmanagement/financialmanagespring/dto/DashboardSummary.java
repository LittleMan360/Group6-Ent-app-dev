package com.enterprisefinancialmanagement.financialmanagespring.dto;

import java.math.BigDecimal;
import java.util.List;

public class DashboardSummary {

    public static class CategorySummary {
        private String category;
        private BigDecimal budget;       // allocation for this category
        private BigDecimal Allocated;        // actual expenses in this category
        private BigDecimal remaining;    // budget - Allocated
        private BigDecimal shareOfTotal; // % of total budget limit

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }

        public BigDecimal getBudget() { return budget; }
        public void setBudget(BigDecimal budget) { this.budget = budget; }

        public BigDecimal getAllocated() { return Allocated; }
        public void setAllocated(BigDecimal Allocated) { this.Allocated = Allocated; }

        public BigDecimal getRemaining() { return remaining; }
        public void setRemaining(BigDecimal remaining) { this.remaining = remaining; }

        public BigDecimal getShareOfTotal() { return shareOfTotal; }
        public void setShareOfTotal(BigDecimal shareOfTotal) { this.shareOfTotal = shareOfTotal; }
    }

    private BigDecimal totalBudget;
    private BigDecimal totalAllocated;
    private BigDecimal totalRemaining;
    private List<CategorySummary> categories;

    public BigDecimal getTotalBudget() { return totalBudget; }
    public void setTotalBudget(BigDecimal totalBudget) { this.totalBudget = totalBudget; }

    public BigDecimal getTotalAllocated() { return totalAllocated; }
    public void setTotalAllocated(BigDecimal totalAllocated) { this.totalAllocated = totalAllocated; }

    public BigDecimal getTotalRemaining() { return totalRemaining; }
    public void setTotalRemaining(BigDecimal totalRemaining) { this.totalRemaining = totalRemaining; }

    public List<CategorySummary> getCategories() { return categories; }
    public void setCategories(List<CategorySummary> categories) { this.categories = categories; }
}
