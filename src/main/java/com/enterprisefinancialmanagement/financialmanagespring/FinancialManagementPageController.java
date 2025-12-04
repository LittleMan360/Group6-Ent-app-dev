package com.enterprisefinancialmanagement.financialmanagespring;

import com.enterprisefinancialmanagement.financialmanagespring.domain.Txn;
import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import com.enterprisefinancialmanagement.financialmanagespring.dto.DashboardSummary;
import com.enterprisefinancialmanagement.financialmanagespring.service.IBudgetService;
import com.enterprisefinancialmanagement.financialmanagespring.service.TxnService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class FinancialManagementPageController {

    private final IBudgetService budgets;
    private final TxnService txns;

    private static final UUID DEMO_USER =
            UUID.fromString("00000000-0000-0000-0000-000000000001");

    // Start with a default of 2000.00, but allow it to be changed from the UI
    private BigDecimal totalBudgetLimit = new BigDecimal("2000.00");

    public FinancialManagementPageController(IBudgetService budgets, TxnService txns) {
        this.budgets = budgets;
        this.txns = txns;
    }

    /* ======= Scenario 1.1 & 1.2: build dashboard summary ======= */
    private DashboardSummary buildSummary(YearMonth month) {
        // All saved budgets (categories + amounts)
        List<Budget> allBudgets = budgets.findAll();

        // 1) Total Allocated (we are calling this "totalAllocated" in Scenario 1)
        BigDecimal totalAllocated = allBudgets.stream()
                .map(Budget::getAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 2) Total Budget = the limit the user set
        BigDecimal totalBudget = totalBudgetLimit;

        // 3) Overall Remaining = limit - sum of budgets
        BigDecimal totalRemaining = totalBudget.subtract(totalAllocated);

        // --- NEW: pull this month's transactions to compute per-category usage ---
        LocalDate from = month.atDay(1);
        LocalDate to = month.atEndOfMonth();

        // only this user's txns, this month
        List<Txn> monthTxns = txns.listForUserBetween(DEMO_USER, from, to);

        // Treat any NEGATIVE amount as an EXPENSE and group by category
        Map<String, BigDecimal> AllocatedByCategory = monthTxns.stream()
                .filter(t -> t.getAmount() != null
                        && t.getAmount().compareTo(BigDecimal.ZERO) < 0)
                .collect(Collectors.groupingBy(
                        Txn::getCategory,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                t -> t.getAmount().negate(), // -50 -> +50 Allocated
                                BigDecimal::add)
                ));

        // Per-category section:
        //  - budget = allocation (limit for that category)
        //  - Allocated = actual expenses for that category (from Txns)
        //  - remaining = budget - Allocated
        //  - shareOfTotal = budget / totalBudgetLimit * 100
        List<DashboardSummary.CategorySummary> cats = new ArrayList<>();
        for (Budget b : allBudgets) {
            DashboardSummary.CategorySummary cs = new DashboardSummary.CategorySummary();
            cs.setCategory(b.getCategory());

            BigDecimal budgetAmount =
                    Optional.ofNullable(b.getAmount()).orElse(BigDecimal.ZERO);

            BigDecimal Allocated = AllocatedByCategory.getOrDefault(b.getCategory(), BigDecimal.ZERO);
            BigDecimal remaining = budgetAmount.subtract(Allocated);

            cs.setBudget(budgetAmount);
            cs.setAllocated(Allocated);
            cs.setRemaining(remaining);

            // How much of the total budget limit this category gets, in %
            BigDecimal share = BigDecimal.ZERO;
            if (totalBudget.compareTo(BigDecimal.ZERO) > 0) {
                share = budgetAmount
                        .multiply(BigDecimal.valueOf(100))
                        .divide(totalBudget, 2, java.math.RoundingMode.HALF_UP);
            }
            cs.setShareOfTotal(share);

            cats.add(cs);
        }

        DashboardSummary summary = new DashboardSummary();
        summary.setTotalBudget(totalBudget);           // total monthly budget limit
        summary.setTotalAllocated(totalAllocated);         // sum of category allocations (Scenario 1 meaning)
        summary.setTotalRemaining(totalRemaining);     // leftover unallocated money
        summary.setCategories(cats);

        return summary;
    }




    /* ======= Pages ======= */

    // Dashboard page (Scenario 1.1 & 1.2)
    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        YearMonth now = YearMonth.now();

        model.addAttribute("specimen", new Budget());
        model.addAttribute("budgets", budgets.findAll());
        model.addAttribute("summary", buildSummary(now));

        // expose the current total budget limit to the view
        model.addAttribute("totalBudget", totalBudgetLimit);

        model.addAttribute("currentPage", "dashboard");
        return "dashboard";
    }

    @GetMapping("/budget/{id}/edit")
    public String editBudget(@PathVariable int id, Model model) {
        YearMonth now = YearMonth.now();
        Budget b = budgets.findById(id);

        model.addAttribute("specimen", b);
        model.addAttribute("budgets", budgets.findAll());
        model.addAttribute("summary", buildSummary(now));
        model.addAttribute("totalBudget", totalBudgetLimit);
        model.addAttribute("currentPage", "dashboard");
        return "dashboard";
    }

    /* ======= NEW: set the overall budget limit from the UI ======= */

    @PostMapping("/setBudgetLimit")
    public String setBudgetLimit(@RequestParam("totalBudget") BigDecimal totalBudget,
                                 Model model) {

        if (totalBudget == null || totalBudget.compareTo(BigDecimal.ZERO) <= 0) {
            model.addAttribute("error", "Total budget must be greater than 0");
            model.addAttribute("specimen", new Budget());
            model.addAttribute("budgets", budgets.findAll());
            model.addAttribute("summary", buildSummary(YearMonth.now()));
            model.addAttribute("totalBudget", totalBudgetLimit);
            model.addAttribute("currentPage", "dashboard");
            return "dashboard";
        }

        this.totalBudgetLimit = totalBudget;
        return "redirect:/dashboard";
    }

    /* ======= Scenario 1.3 & 1.4: save with validation ======= */

    @PostMapping("/saveBudget")
    public String saveBudget(
            @ModelAttribute("specimen") Budget budget,
            @RequestParam(name = "override", defaultValue = "false") boolean override,
            Model model
    ) {
        List<Budget> existing = budgets.findAll();

        // ---- Scenario 1.3: allocations exceed total budget LIMIT ----
        BigDecimal newSum = existing.stream()
                .filter(b -> b.getBudgetId() != budget.getBudgetId())
                .map(Budget::getAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(Optional.ofNullable(budget.getAmount()).orElse(BigDecimal.ZERO));

        if (newSum.compareTo(totalBudgetLimit) > 0) {
            model.addAttribute("error", "Category allocations exceed total budget");
            model.addAttribute("budgets", existing);
            model.addAttribute("summary", buildSummary(YearMonth.now()));
            model.addAttribute("totalBudget", totalBudgetLimit);
            model.addAttribute("currentPage", "dashboard");
            return "dashboard";
        }

        // ---- Scenario 1.4: allocation < already Allocated (unless override) ----
        YearMonth month = YearMonth.now();
        LocalDate from = month.atDay(1);
        LocalDate to = month.atEndOfMonth();

        String targetCategory = Optional.ofNullable(budget.getCategory())
                .orElse("")
                .trim();

        BigDecimal AllocatedInCategory = txns.listForUserBetween(DEMO_USER, from, to).stream()
                .filter(t -> t != null)
                .filter(t -> t.getType() != null && t.getType() == Txn.Type.EXPENSE)
                .filter(t -> {
                    String txnCat = Optional.ofNullable(t.getCategory()).orElse("").trim();
                    return !targetCategory.isEmpty() && txnCat.equalsIgnoreCase(targetCategory);
                })
                .map(t -> {
                    BigDecimal amt = t.getAmount();
                    return (amt == null) ? BigDecimal.ZERO : amt.abs();
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (!override &&
                budget.getAmount() != null &&
                budget.getAmount().compareTo(AllocatedInCategory) < 0) {

            model.addAttribute("error", "Allocation cannot be less than already Allocated");
            model.addAttribute("budgets", existing);
            model.addAttribute("summary", buildSummary(month));
            model.addAttribute("totalBudget", totalBudgetLimit);
            model.addAttribute("currentPage", "dashboard");
            return "dashboard";
        }

        // If we got here, all validations passed
        budgets.save(budget);
        return "redirect:/dashboard";
    }

    @PostMapping("/budget/{id}/delete")
    public String deleteBudget(@PathVariable int id) {
        budgets.deleteById(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/report")
    public String report(Model model) {
        YearMonth now = YearMonth.now();
        model.addAttribute("summary", buildSummary(now));   // <-- important
        model.addAttribute("budgets", budgets.findAll());
        model.addAttribute("currentPage", "report");
        return "report";
    }

    @GetMapping("/getstarted")
    public String getStarted(Model model) {
        model.addAttribute("currentPage", "getstarted");
        return "getstarted"; // getstarted.html
    }
}
