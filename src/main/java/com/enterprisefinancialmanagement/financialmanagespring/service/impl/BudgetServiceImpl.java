package com.enterprisefinancialmanagement.financialmanagespring.service.impl;

import com.enterprisefinancialmanagement.financialmanagespring.service.IBudgetService;
import com.enterprisefinancialmanagement.financialmanagespring.web.model.Budget;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BudgetServiceImpl implements IBudgetService {

    private final Map<Long, Budget> store = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public BudgetServiceImpl() {
        // seed one sample so the table isn't empty
        Budget sample = new Budget();
        sample.setId(seq.incrementAndGet());
        sample.setName("Monthly Groceries");
        sample.setCategory("Food");
        sample.setAmount(new BigDecimal("250.00"));
        store.put(sample.getId(), sample);
    }

    @Override
    public List<Budget> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Budget> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean delete(Long id) {
        return store.remove(id) != null;
    }

    @Override
    public Budget create(Budget budget) {
        if (budget == null) {
            throw new IllegalArgumentException("Budget cannot be null");
        }
        long id = seq.incrementAndGet();
        budget.setId(id);
        store.put(id, budget);
        return budget;
    }

}
