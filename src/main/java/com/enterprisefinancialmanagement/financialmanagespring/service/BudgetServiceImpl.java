package com.enterprisefinancialmanagement.financialmanagespring.service;

import com.enterprisefinancialmanagement.financialmanagespring.dao.IBudgetRepository;
import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BudgetServiceImpl implements IBudgetService{

    private final IBudgetRepository budgetRepository;

    @Autowired
    public BudgetServiceImpl(IBudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public List<Budget> getAllBudgets() {
        return List.of();
    }

    @Override
    public Optional<Budget> getBudgetById(int id) {
        return Optional.empty();
    }

    @Override
    public Budget createBudget(Budget budget) {
        return null;
    }

    @Override
    public void deleteBudget(int id) {

    }
}
