package com.enterprisefinancialmanagement.financialmanagespring;

import com.enterprisefinancialmanagement.financialmanagespring.service.IBudgetService;
import com.enterprisefinancialmanagement.financialmanagespring.web.model.Budget;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class FinancialManagementPageController {

    private final IBudgetService budgetService;

    public FinancialManagementPageController(IBudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public String showDashboard(Model model) {
        model.addAttribute("budgets", budgetService.findAll());
        model.addAttribute("budget", new Budget());
        return "dashboard";
    }

    @PostMapping("/add")
    public String addBudget(@Valid @ModelAttribute("budget") Budget budget,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("budgets", budgetService.findAll());
            return "dashboard";
        }
        budgetService.create(budget);
        return "redirect:/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteBudget(@PathVariable Long id) {
        budgetService.delete(id);
        return "redirect:/dashboard";
    }
}
