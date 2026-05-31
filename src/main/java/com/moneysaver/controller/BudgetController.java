package com.moneysaver.controller;

import com.moneysaver.model.Category;
import com.moneysaver.model.IncomeType;
import com.moneysaver.model.Transaction;
import com.moneysaver.service.BudgetTracker;

import java.util.Map;

public class BudgetController {

    private BudgetTracker budgetTracker;

    public BudgetController(BudgetTracker budgetTracker) {
        this.budgetTracker = budgetTracker;
    }

    public void addIncome(double amount, IncomeType type) {
        budgetTracker.addTransaction(
                new Transaction(amount, type)
        );
    }

    public void addExpense(double amount, Category category) {
        budgetTracker.addTransaction(
                new Transaction(amount, category)
        );
    }

    public double getIncome() {
        return budgetTracker.getTotalIncome();
    }

    public double getExpenses() {
        return budgetTracker.getTotalExpenses();
    }

    public double getBalance() {
        return budgetTracker.getBalance();
    }

    public Map<Category, Double> getSpendingByCategory() {
        return budgetTracker.getSpendingByCategory();
    }
}