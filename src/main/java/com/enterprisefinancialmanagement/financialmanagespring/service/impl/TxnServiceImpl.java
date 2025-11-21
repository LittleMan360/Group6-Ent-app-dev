package com.enterprisefinancialmanagement.financialmanagespring.service.impl;

import com.enterprisefinancialmanagement.financialmanagespring.dao.TxnDao;
import com.enterprisefinancialmanagement.financialmanagespring.domain.Txn;
import com.enterprisefinancialmanagement.financialmanagespring.service.TxnService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class TxnServiceImpl implements TxnService {

    private final TxnDao txns;

    public TxnServiceImpl(TxnDao txns) {
        this.txns = txns;
    }

    @Override
    @Transactional
    public Txn addExpense(UUID userId, BigDecimal amount, String category, LocalDate date, String note) {
        Txn t = new Txn();
        t.setUserId(userId);
        t.setAmount(amount.abs().negate()); // expenses negative
        t.setType(Txn.Type.EXPENSE);
        t.setCategory(category);
        t.setDate(date != null ? date : LocalDate.now());
        t.setNote(note);
        return txns.save(t);
    }

    @Override
    @Transactional
    public Txn addIncome(UUID userId, BigDecimal amount, String source, LocalDate date, String note) {
        Txn t = new Txn();
        t.setUserId(userId);
        t.setAmount(amount.abs()); // income positive
        t.setType(Txn.Type.INCOME);
        t.setCategory(source);
        t.setDate(date != null ? date : LocalDate.now());
        t.setNote(note);
        return txns.save(t);
    }

    @Override public List<Txn> listForUser(UUID userId) { return txns.findByUser(userId); }
    @Override public List<Txn> listForUserBetween(UUID userId, LocalDate from, LocalDate to) { return txns.findByUserAndDateRange(userId, from, to); }
    @Override public BigDecimal getNetForUserBetween(UUID userId, LocalDate from, LocalDate to) {
        return listForUserBetween(userId, from, to).stream()
                .map(Txn::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    @Override @Transactional public void delete(UUID txnId) { txns.delete(txnId); }
}
