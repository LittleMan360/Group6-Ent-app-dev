package com.enterprisefinancialmanagement.financialmanagespring;

import com.enterprisefinancialmanagement.financialmanagespring.dao.IBudgetRepository;
import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class FinancialManagementController {

    private final IBudgetRepository budgetRepository;

    public FinancialManagementController(IBudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    //Sets the dashboard as the default page
    @RequestMapping("/") //Sets the dashboard as the default page
    public String index(Model model) {
        model.addAttribute("specimen", new Budget());
        return "dashboard";
    }

    //Returns a list of all budgets
    @GetMapping("/budget")
    public ResponseEntity<List<Budget>> fetchAllBudgets() {
        List<Budget> budgets = budgetRepository.findAll();
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }

    // Returns a specific budget based on ID number
    @GetMapping("/budget/{id}")
    public ResponseEntity<Budget> fetchBudgetById(@PathVariable("id") int id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        return budget.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Creates a new entry
    @PostMapping(value = "/budget", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        Budget savedBudget = budgetRepository.save(budget);
        return new ResponseEntity<>(savedBudget, HttpStatus.CREATED);
    }

    //Deletes an entry based on ID
    @DeleteMapping("/budget/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable("id") int id) {
        if (budgetRepository.existsById(id)) {
            budgetRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
