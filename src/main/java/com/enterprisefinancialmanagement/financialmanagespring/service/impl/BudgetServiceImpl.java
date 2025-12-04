package com.enterprisefinancialmanagement.financialmanagespring.service.impl;

import com.enterprisefinancialmanagement.financialmanagespring.dto.Budget;
import com.enterprisefinancialmanagement.financialmanagespring.service.IBudgetService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BudgetServiceImpl implements IBudgetService {

    private final Map<Integer, Budget> store = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    @Override
    public List<Budget> findAll() {
        List<Budget> list = new ArrayList<>(store.values());
        list.sort(Comparator.comparingInt(Budget::getBudgetId));
        return list;
    }

    @Override
    public Budget findById(int id) {
        return store.get(id);
    }

    @Override
    public Budget save(Budget budget) {
        // If ID is 0, it's NEW; otherwise, UPDATE existing
        if (budget.getBudgetId() == 0) {
            budget.setBudgetId(idCounter.getAndIncrement());
        }
        store.put(budget.getBudgetId(), budget); // overwrites when editing
        return budget;
    }

    @Override
    public void deleteById(int id) {
        store.remove(id);
    }
}
