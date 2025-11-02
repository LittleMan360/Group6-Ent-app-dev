package com.enterprisefinancialmanagement.financialmanagespring.service;

import com.enterprisefinancialmanagement.financialmanagespring.web.model.Budget;

import java.util.List;
import java.util.Optional;

public interface IBudgetService {
    List<Budget> findAll();
    Optional<Budget> findById(Long id);
    Budget create(Budget budget);
    boolean delete(Long id);
}

