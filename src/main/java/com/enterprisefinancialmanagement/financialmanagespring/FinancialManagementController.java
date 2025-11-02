package com.enterprisefinancialmanagement.financialmanagespring;

import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Controller for managing budget pages and dashboard.
 * Handles form binding, view navigation, and demo logic.
 * TODO: Move business logic (auto-increment, date defaults) to a service layer.
 */

@RestController
@RequestMapping("/budget")
public class FinancialManagementController {

    @GetMapping
    public ResponseEntity<List<Budget>> fetchAllBudgets() {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> fetchBudgetById(@PathVariable("id") String id) {
        Budget budget = new Budget();
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }
/**
 * Handles submission of a budget form, sets ID and default dates,
 * then adds budget to the in-memory list.
 */

    @PostMapping(consumes="application/json", produces="application/json")
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        return new ResponseEntity<>(budget, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}