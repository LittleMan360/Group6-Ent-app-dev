package com.enterprisefinancialmanagement.financialmanagespring.service;
import java.util.List;


import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;

public interface IBudgetService {
    Budget createBudget(Budget budget);
    Budget getBudgetById(String id);
    List<Budget> getAllBudgets();
    boolean deleteBudget(String id);
}--
