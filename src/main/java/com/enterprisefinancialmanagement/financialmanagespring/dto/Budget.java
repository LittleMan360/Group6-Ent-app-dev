package com.enterprisefinancialmanagement.financialmanagespring.dto;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

  /**
  * Data Transfer Object
  * Java class for Budget 
  * @author Melissa Manzon
  */

  @Entity
  @Table(name = "budgets")
  @Data
public class  Budget {

    //Id has an auto increment primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int budgetId;

    //Budget name can't be blank or exceed 50 characters
    @NotBlank(message = "Budget name is required")
    @Size(max = 50, message = "Budget cannot exceed 50 characters")
    private String budgetName;

    //Description is optional but cannot exceed 300 characters
    @Size(max = 300, message = "Description cannot exceed 300 characters")
    private String description;

    //Dates cannot be null
    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;


}
