package com.moneysaver.model;

import java.time.LocalDate;

public class Transaction {

    private String type; // income/expense
    private double amount;

    private Category category; // only for filtering expenses
    private IncomeType incomeType; // only for filtering income

    private LocalDate date;

    // EXPENSE constructor
    public Transaction(double amount, Category category) {
        this.type = "expense";
        this.amount = amount;
        this.category = category;
        this.incomeType = null;
        this.date = LocalDate.now();
    }

    // INCOME constructor
    public Transaction(double amount, IncomeType incomeType) {
        this.type = "income";
        this.amount = amount;
        this.incomeType = incomeType;
        this.category = null;
        this.date = LocalDate.now();
    }

    public String getType() { return type;}

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public IncomeType getIncomeType() {return incomeType;}

    public LocalDate getDate() {return date;}
}