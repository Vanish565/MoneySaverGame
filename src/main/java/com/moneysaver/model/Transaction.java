package com.moneysaver.model;

import java.time.LocalDate;

public class Transaction {

    private FilterType type; // income/expense
    private double amount;

    private ExpenseType expenseType; // only for filtering expenses
    private IncomeType incomeType; // only for filtering income

    private LocalDate date;

    // EXPENSE constructor
    public Transaction(double amount, ExpenseType expenseType) {
        this.type = FilterType.EXPENSE;
        this.amount = amount;
        this.expenseType = expenseType;
        this.incomeType = null;
        this.date = LocalDate.now();
    }

    // INCOME constructor
    public Transaction(double amount, IncomeType incomeType) {
        this.type = FilterType.INCOME;
        this.amount = amount;
        this.incomeType = incomeType;
        this.expenseType = null;
        this.date = LocalDate.now();
    }

    public FilterType getType() { return this.type;}

    public double getAmount() {
        return amount;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public IncomeType getIncomeType() {return incomeType;}

    public LocalDate getDate() {return date;}
}