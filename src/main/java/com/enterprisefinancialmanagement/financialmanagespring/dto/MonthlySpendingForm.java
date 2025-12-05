package com.enterprisefinancialmanagement.financialmanagespring.dto;

import java.util.ArrayList;
import java.util.List;

public class MonthlySpendingForm {

    private List<MonthlySpendingRow> rows = new ArrayList<>();

    public List<MonthlySpendingRow> getRows() {
        return rows;
    }

    public void setRows(List<MonthlySpendingRow> rows) {
        this.rows = rows;
    }
}
