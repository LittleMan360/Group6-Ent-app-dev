package com.enterprisefinancialmanagement.financialmanagespring.service.impl;

import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BudgetServiceImplTest {

    private BudgetServiceImpl svc;

    @BeforeEach
    void setUp() {
        svc = new BudgetServiceImpl();
    }

    @Test
    void saveAssignsIdWhenNew() {
        Budget b = new Budget();
        b.setBudgetId(0);
        b.setBudgetName("Groceries");
        b.setAmount(BigDecimal.valueOf(300));

        Budget saved = svc.save(b);

        assertNotNull(saved);
        assertTrue(saved.getBudgetId() > 0);
        assertEquals("Groceries", saved.getBudgetName());
    }

    @Test
    void findAllAndDeleteByIdWork() {
        Budget b = new Budget();
        b.setBudgetId(0);
        b.setBudgetName("Utilities");
        svc.save(b);

        var all = svc.findAll();
        assertFalse(all.isEmpty());

        int id = all.get(0).getBudgetId();
        svc.deleteById(id);
        assertNull(svc.findById(id));
    }
}
