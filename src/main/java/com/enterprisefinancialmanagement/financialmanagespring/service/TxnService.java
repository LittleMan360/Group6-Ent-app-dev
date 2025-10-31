package com.enterprisefinancialmanagement.financialmanagespring.service;

import com.enterprisefinancialmanagement.financialmanagespring.domain.Txn;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public interface TxnService {
    Txn addExpense(UUID userId, BigDecimal amount, String category, LocalDate date, String note);
    Txn addIncome(UUID userId, BigDecimal amount, String source, LocalDate date, String note);

    List<Txn> listForUser(UUID userId);
    List<Txn> listForUserBetween(UUID userId, LocalDate from, LocalDate to);

    BigDecimal getNetForUserBetween(UUID userId, LocalDate from, LocalDate to);

    void delete(UUID txnId);
}
