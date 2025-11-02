package com.enterprisefinancialmanagement.financialmanagespring;

import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FinancialManagementController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("specimen", new Budget());
        return "dashboard";
    }

    @GetMapping("/budget")
    public ResponseEntity fetchAllBudegts() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/budget/{id}/")
    public ResponseEntity fetchBudgetById(@PathVariable( "id") String id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value="/budget", consumes="application/json", produces="application/json")
    public Budget createBudget(@RequestBody Budget budget) {

        return budget;
    }

    @DeleteMapping("/budget/{id}")
    public ResponseEntity deleteBudget(@PathVariable("id") String id) {

        return new ResponseEntity(HttpStatus.OK);
    }
}
