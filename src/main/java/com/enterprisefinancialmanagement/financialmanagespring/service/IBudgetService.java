package com.enterprisefinancialmanagement.financialmanagespring.service;

import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;

import java.util.List;

public interface IBudgetService {
    List<Budget> findAll();
    Budget findById(int id);
    Budget save(Budget budget);
    void deleteById(int id);
}
