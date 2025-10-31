package com.enterprisefinancialmanagement.financialmanagespring.dto;


import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
  * Data Transfer Object
  * Java class for Budget 
  * @author Melissa Manzon
  */
public @Data
class  Budget {
    private int budgetId;
    private String budgetName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
