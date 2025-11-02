package com.enterprisefinancialmanagement.financialmanagespring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for managing budget pages and dashboard.
 * Handles form binding, view navigation, and demo logic.
 * TODO: Move business logic (auto-increment, date defaults) to a service layer.
 */
/**
 * Controller for managing the dashboard and budget pages.
 * Contains methods to show dashboards and save/retrieve budgets.
 */
@Controller
public class FinancialManagementPageController {
    private static final Logger log = LoggerFactory.getLogger(FinancialManagementPageController.class);

    // In-memory list to store budgets
    private final List<Budget> budgetList = new ArrayList<>();
    private int nextBudgetId = 1; // auto-increment ID
    /**
     * Shows the dashboard page and populates the view model with budget data.
     * @param model Spring Model object for passing attributes to the view.
     * @return The dashboard HTML template.
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("specimen", new Budget()); // for form binding
        model.addAttribute("budgets", budgetList);      // display list in table
        model.addAttribute("currentPage", "dashboard");
        return "dashboard"; // dashboard.html
    }
/**
 * Handles submission of a budget form, sets ID and default dates,
 * then adds budget to the in-memory list.
 */

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
        log.info("Saved budget: {}", budget.getDescription());

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