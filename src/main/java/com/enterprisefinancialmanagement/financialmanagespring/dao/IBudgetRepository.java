package com.enterprisefinancialmanagement.financialmanagespring.dao;

import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IBudgetRepository extends JpaRepository<Budget, Integer> {


}
