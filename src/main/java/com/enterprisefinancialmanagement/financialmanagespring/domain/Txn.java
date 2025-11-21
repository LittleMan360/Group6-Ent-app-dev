package com.enterprisefinancialmanagement.financialmanagespring.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Txn {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private BigDecimal amount;  // income = positive, expense = negative

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;  // INCOME or EXPENSE

    private String category;    // e.g., "Groceries" or "Paycheck"
    private LocalDate date;     // transaction date
    private String note;        // optional memo

    public enum Type { INCOME, EXPENSE }

    // --- Getters and Setters ---
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
