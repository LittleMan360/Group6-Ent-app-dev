package com.enterprisefinancialmanagement.financialmanagespring;

import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import com.enterprisefinancialmanagement.financialmanagespring.service.IBudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * MVC controller that serves Thymeleaf views under src/main/resources/templates.
 * It connects the HTML in dashboard.html / report.html / getstarted.html
 * to the backend Budget service.
 */

@Controller
public class FinancialManagementPageController {

    private final IBudgetService budgets;

    public FinancialManagementPageController(IBudgetService budgets) {
        this.budgets = budgets;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        // Object the form binds to
        model.addAttribute("specimen", new Budget());
        // List shown in the table
        model.addAttribute("budgets", budgets.findAll());
        // Used by navbar.html to highlight current tab
        model.addAttribute("currentPage", "dashboard");
        return "dashboard"; // dashboard.html
    }

    @PostMapping("/saveBudget")
    public String saveBudget(@ModelAttribute("specimen") Budget budget) {
        budgets.save(budget);
        // Redirect so browser refresh doesn't re-submit the form
        return "redirect:/dashboard";
    }

    @GetMapping("/budget/{id}/edit")
    public String editBudget(@PathVariable("id") int id, Model model) {
        Budget existing = budgets.findById(id);
        if (existing == null) {
            return "redirect:/dashboard";
        }
        model.addAttribute("specimen", existing);
        model.addAttribute("budgets", budgets.findAll());
        model.addAttribute("currentPage", "dashboard");
        return "dashboard"; // re-use same page with form populated
    }

    // === NEW: delete a budget ===
    @PostMapping("/budget/{id}/delete")
    public String deleteBudget(@PathVariable("id") int id) {
        budgets.deleteById(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/report")
    public String report(Model model) {
        model.addAttribute("budgets", budgets.findAll());
        model.addAttribute("currentPage", "report");
        return "report"; // report.html
    }

    @GetMapping("/getstarted")
    public String getStarted(Model model) {
        model.addAttribute("currentPage", "getstarted");
        return "getstarted"; // getstarted.html
    }
}
