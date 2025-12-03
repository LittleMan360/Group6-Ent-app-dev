package com.enterprisefinancialmanagement.financialmanagespring.service.impl;

import com.enterprisefinancialmanagement.financialmanagespring.dao.TxnDao;
import com.enterprisefinancialmanagement.financialmanagespring.domain.Txn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
class TxnServiceImplTest {

    @Mock
    private TxnDao txnDao;

    @InjectMocks
    private TxnServiceImpl txnService;

    @Test
    void addExpenseCreatesNegativeAmountAndExpenseType() {
        UUID userId = UUID.randomUUID();
        when(txnDao.save(any())).thenAnswer((Answer<Txn>) invocation -> invocation.getArgument(0));

        Txn t = txnService.addExpense(userId, BigDecimal.valueOf(120), "Groceries", null, "note");

        assertNotNull(t);
        assertEquals(Txn.Type.EXPENSE, t.getType());
        assertTrue(t.getAmount().compareTo(BigDecimal.ZERO) <= 0);
        assertEquals("Groceries", t.getCategory());
        assertNotNull(t.getDate());
    }

    @Test
    void addIncomeCreatesPositiveAmountAndIncomeType() {
        UUID userId = UUID.randomUUID();
        when(txnDao.save(any())).thenAnswer((Answer<Txn>) invocation -> invocation.getArgument(0));

        Txn t = txnService.addIncome(userId, BigDecimal.valueOf(500), "Paycheck", LocalDate.now(), "salary");

        assertNotNull(t);
        assertEquals(Txn.Type.INCOME, t.getType());
        assertTrue(t.getAmount().compareTo(BigDecimal.ZERO) >= 0);
        assertEquals("Paycheck", t.getCategory());
    }

    @Test
    void getNetForUserBetweenSumsAmounts() {
        UUID userId = UUID.randomUUID();
        Txn a = new Txn(); a.setAmount(BigDecimal.valueOf(100));
        Txn b = new Txn(); b.setAmount(BigDecimal.valueOf(-30));
        doReturn(List.of(a, b)).when(txnDao).findByUserAndDateRange(userId, LocalDate.of(2020,1,1), LocalDate.of(2020,12,31));

        var net = txnService.getNetForUserBetween(userId, LocalDate.of(2020,1,1), LocalDate.of(2020,12,31));

        assertEquals(BigDecimal.valueOf(70), net);
    }
}
