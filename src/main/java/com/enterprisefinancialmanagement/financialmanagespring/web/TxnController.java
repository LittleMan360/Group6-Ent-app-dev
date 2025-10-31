package com.enterprisefinancialmanagement.financialmanagespring.web;


import com.enterprisefinancialmanagement.financialmanagespring.domain.Txn;
import com.enterprisefinancialmanagement.financialmanagespring.service.TxnService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/txns")
public class TxnController {

    private final TxnService txns;

    public TxnController(TxnService txns) {
        this.txns = txns;
    }

    @PostMapping("/income")
    public Txn addIncome(@RequestParam UUID userId,
                         @RequestParam BigDecimal amount,
                         @RequestParam String source,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                         @RequestParam(required = false) String note) {
        return txns.addIncome(userId, amount, source, date, note);
    }

    @PostMapping("/expense")
    public Txn addExpense(@RequestParam UUID userId,
                          @RequestParam BigDecimal amount,
                          @RequestParam String category,
                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                          @RequestParam(required = false) String note) {
        return txns.addExpense(userId, amount, category, date, note);
    }

    @GetMapping
    public List<Txn> list(@RequestParam UUID userId) {
        return txns.listForUser(userId);
    }
}

