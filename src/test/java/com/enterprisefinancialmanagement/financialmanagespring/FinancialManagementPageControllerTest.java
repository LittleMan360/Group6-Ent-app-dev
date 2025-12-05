package com.enterprisefinancialmanagement.financialmanagespring;

import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import com.enterprisefinancialmanagement.financialmanagespring.dto.DashboardSummary;
import com.enterprisefinancialmanagement.financialmanagespring.dto.MonthlySpendingForm;
import com.enterprisefinancialmanagement.financialmanagespring.dto.MonthlySpendingRow;
import com.enterprisefinancialmanagement.financialmanagespring.domain.Txn;
import com.enterprisefinancialmanagement.financialmanagespring.service.IBudgetService;
import com.enterprisefinancialmanagement.financialmanagespring.service.TxnService;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FinancialManagementPageControllerTest {

    @Test
    void monthlySpendingBuildsFormWithPrefilledSpent() {
        IBudgetService budgets = mock(IBudgetService.class);
        TxnService txns = mock(TxnService.class);
        FinancialManagementPageController controller =
                new FinancialManagementPageController(budgets, txns);

        Budget foodBudget = new Budget();
        foodBudget.setBudgetId(1);
        foodBudget.setBudgetName("Food Budget");
        foodBudget.setCategory("Food");
        foodBudget.setAmount(BigDecimal.valueOf(300));

        Budget gamesBudget = new Budget();
        gamesBudget.setBudgetId(2);
        gamesBudget.setBudgetName("Games Budget");
        gamesBudget.setCategory("Games");
        gamesBudget.setAmount(BigDecimal.valueOf(200));

        when(budgets.findAll()).thenReturn(List.of(foodBudget, gamesBudget));

        Txn t1 = new Txn();
        t1.setId(UUID.randomUUID());
        t1.setType(Txn.Type.EXPENSE);
        t1.setCategory("Food");
        t1.setAmount(BigDecimal.valueOf(-40)); // 40 spent

        Txn t2 = new Txn();
        t2.setId(UUID.randomUUID());
        t2.setType(Txn.Type.EXPENSE);
        t2.setCategory("Games");
        t2.setAmount(BigDecimal.valueOf(-10)); // 10 spent

        when(txns.listForUserBetween(any(), any(), any())).thenReturn(List.of(t1, t2));

        Model model = new ExtendedModelMap();

        String view = controller.monthlySpending("2025-01", model);

        assertThat(view).isEqualTo("monthly-spending");
        assertThat(model.getAttribute("currentPage")).isEqualTo("monthly-spending");
        assertThat(model.getAttribute("selectedMonth")).isEqualTo("2025-01");

        DashboardSummary summary = (DashboardSummary) model.getAttribute("summary");
        assertThat(summary).isNotNull();
        assertThat(summary.getCategories())
                .extracting(DashboardSummary.CategorySummary::getCategory)
                .containsExactlyInAnyOrder("Food", "Games");

        MonthlySpendingForm form = (MonthlySpendingForm) model.getAttribute("form");
        assertThat(form).isNotNull();
        assertThat(form.getRows()).hasSize(2);

        MonthlySpendingRow foodRow = form.getRows().stream()
                .filter(r -> "Food".equals(r.getCategory()))
                .findFirst()
                .orElseThrow();

        MonthlySpendingRow gamesRow = form.getRows().stream()
                .filter(r -> "Games".equals(r.getCategory()))
                .findFirst()
                .orElseThrow();

        assertThat(foodRow.getBudget()).isEqualByComparingTo("300");
        assertThat(foodRow.getSpent()).isEqualByComparingTo("40");

        assertThat(gamesRow.getBudget()).isEqualByComparingTo("200");
        assertThat(gamesRow.getSpent()).isEqualByComparingTo("10");
    }

    @Test
    void saveMonthlySpendingAddsExpenseWhenDesiredGreaterThanCurrent() {
        IBudgetService budgets = mock(IBudgetService.class);
        TxnService txns = mock(TxnService.class);
        FinancialManagementPageController controller =
                new FinancialManagementPageController(budgets, txns);

        Txn existing = new Txn();
        existing.setId(UUID.randomUUID());
        existing.setType(Txn.Type.EXPENSE);
        existing.setCategory("Food");
        existing.setAmount(BigDecimal.valueOf(-40)); // currently 40 spent

        when(txns.listForUserBetween(any(), any(), any())).thenReturn(List.of(existing));

        MonthlySpendingRow row = new MonthlySpendingRow();
        row.setCategory("Food");
        row.setBudget(BigDecimal.valueOf(300));
        row.setSpent(BigDecimal.valueOf(100)); // user wants 100 total spent

        MonthlySpendingForm form = new MonthlySpendingForm();
        form.getRows().add(row);

        String result = controller.saveMonthlySpending("2025-01", form);

        assertThat(result).isEqualTo("redirect:/monthly-spending?month=2025-01");

        verify(txns).addExpense(
                any(UUID.class),
                eq(BigDecimal.valueOf(60)), // 100 desired - 40 current
                eq("Food"),
                eq(YearMonth.parse("2025-01").atEndOfMonth()),
                eq("Monthly spending update")
        );
    }
}
