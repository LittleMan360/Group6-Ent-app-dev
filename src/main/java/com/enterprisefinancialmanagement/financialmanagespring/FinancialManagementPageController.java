package com.enterprisefinancialmanagement.financialmanagespring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FinancialManagementPageController {

    // In-memory list to store budgets
    private final List<Budget> budgetList = new ArrayList<>();
    private int nextBudgetId = 1; // auto-increment ID

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("specimen", new Budget()); // for form binding
        model.addAttribute("budgets", budgetList);      // display list in table
        model.addAttribute("currentPage", "dashboard");
        return "dashboard"; // dashboard.html
    }

    @PostMapping("/saveBudget")
    public String saveBudget(@ModelAttribute("specimen") Budget budget) {
        // Auto-increment ID
        budget.setBudgetId(nextBudgetId++);
        // Set default start date if null
        if (budget.getStartDate() == null) {
            budget.setStartDate(LocalDate.now());
        }
        // Set default end date if null (1 year from now)
        if (budget.getEndDate() == null) {
            budget.setEndDate(budget.getStartDate().plusYears(1));
        }
        // Add to the list
        budgetList.add(budget);
        System.out.println("Saved budget: " + budget.getDescription());
        return "redirect:/dashboard";
    }

    @GetMapping("/report")
    public String report(Model model) {
        model.addAttribute("budgets", budgetList);   // show all saved budgets
        model.addAttribute("currentPage", "report");
        return "report"; // report.html
    }

    @GetMapping("/getstarted")
    public String getStarted(Model model) {
        model.addAttribute("currentPage", "getstarted");
        return "getstarted"; // getstarted.html
    }
}