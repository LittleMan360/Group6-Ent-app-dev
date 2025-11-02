package com.enterprisefinancialmanagement.financialmanagespring.dto;


import lombok.Data;
import java.time.LocalDate;

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
