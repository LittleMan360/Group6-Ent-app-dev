package com.enterprisefinancialmanagement.financialmanagespring.service;

import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import java.util.List;
import java.util.Optional;

public interface IBudgetService {

    List<Budget> getAllBudgets();
    Optional<Budget> getBudgetById(int id);
    Budget createBudget(Budget budget);
    void deleteBudget(int id);

}
