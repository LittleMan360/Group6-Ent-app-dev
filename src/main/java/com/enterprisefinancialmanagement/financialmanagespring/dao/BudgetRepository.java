package com.enterprisefinancialmanagement.financialmanagespring.dao;

import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUserId(String userId);
    Optional<Budget> findByUserIdAndMonth(String userId, String month);
}