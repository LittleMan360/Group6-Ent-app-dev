package com.enterprisefinancialmanagement.financialmanagespring;

import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import com.enterprisefinancialmanagement.financialmanagespring.service.IBudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget")
public class FinancialManagementController {

    private final IBudgetService budgets;

    public FinancialManagementController(IBudgetService budgets) {
        this.budgets = budgets;
    }

    @GetMapping
    public ResponseEntity<List<Budget>> fetchAllBudgets() {
        return new ResponseEntity<>(budgets.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> fetchBudgetById(@PathVariable("id") int id) {
        Budget budget = budgets.findById(id);
        if (budget == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        Budget saved = budgets.save(budget);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

}