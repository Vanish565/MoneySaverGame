package com.moneysaver.controller;

import com.moneysaver.model.ExpenseType;
import com.moneysaver.model.FilterType;
import com.moneysaver.model.IncomeType;
import com.moneysaver.model.Transaction;
import com.moneysaver.service.BudgetTracker;

import java.time.LocalDate;
import java.util.List;
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

    public void addExpense(double amount, ExpenseType expenseType) {
        budgetTracker.addTransaction(
                new Transaction(amount, expenseType)
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

    public double getExpenseRate() {
        return budgetTracker.getExpenseRate();
    }

    public int getDaysUntilBroke() {
        return budgetTracker.getDaysUntilBroke();
    }

    public Map<ExpenseType, Double> getSpendingByCategory() {
        return budgetTracker.getSpendingByCategory();
    }

    public Map<IncomeType, Double> getIncomeByCategory() {
        return budgetTracker.getIncomeByCategory();
    }

    public List<Transaction> getTransactions(){
        return budgetTracker.getTransactions();
    }

    public List<Transaction> getTransactionsByType(FilterType type)
    {
        return budgetTracker.getTransactionsByType(type);
    }

    public List<Transaction> getTransactionsBetweenDates(LocalDate startDate, LocalDate endDate)
    {
        return budgetTracker.getTransactionsBetweenDate(startDate, endDate);
    }
}
