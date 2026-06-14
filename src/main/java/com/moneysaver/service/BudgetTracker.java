package com.moneysaver.service;

import com.moneysaver.model.ExpenseType;
import com.moneysaver.model.FilterType;
import com.moneysaver.model.IncomeType;
import com.moneysaver.model.Transaction;
import com.moneysaver.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.*;

public class BudgetTracker {
//    private List<Transaction> transactions =  new ArrayList<>();

    private TransactionRepository repository;
    private List<Transaction> transactions;

    // constructor for testing purposes
    public BudgetTracker()
    {
        this.transactions = new ArrayList<>();
    }

    // constructor
    public BudgetTracker(TransactionRepository repository) {
        this.repository = repository;
        this.transactions = repository.loadTransactions();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        if(this.repository != null)
        {
            repository.saveTransactions(transactions);
        }

    }

    public double getTotalIncome(){
        double total = 0;
        for(Transaction transaction : transactions){
            if(transaction.getType().equals(FilterType.INCOME))
            {
                total += transaction.getAmount();
            }
        }
        return total;
    }

    public double getTotalExpenses() {
        double total = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(FilterType.EXPENSE)) {
                total += transaction.getAmount();
            }
        }
        return total;
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpenses();
    }

    public double getAverageDailyExpenses() {
        double totalExpenses = getTotalExpenses();

        if (totalExpenses == 0) {
            return 0;
        }

        LocalDate firstExpenseDate = null;
        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(FilterType.EXPENSE)) {
                LocalDate transactionDate = transaction.getDate();
                if (firstExpenseDate == null || transactionDate.isBefore(firstExpenseDate)) {
                    firstExpenseDate = transactionDate;
                }
            }
        }

        long daysTracked = java.time.temporal.ChronoUnit.DAYS.between(firstExpenseDate, LocalDate.now()) + 1;
        return totalExpenses / daysTracked;
    }

    public double getExpenseRate() {
        return getAverageDailyExpenses();
    }

    public int getDaysUntilBroke() {
        double balance = getBalance();

        if (balance <= 0) {
            return 0;
        }

        double averageDailyExpenses = getAverageDailyExpenses();
        if (averageDailyExpenses == 0) {
            return -1;
        }

        return (int) Math.ceil(balance / averageDailyExpenses);
    }

    public Map<ExpenseType, Double> getSpendingByCategory() {
        Map<ExpenseType, Double> spending = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(FilterType.EXPENSE))
            {
                ExpenseType expenseType = transaction.getExpenseType();
                double amount = transaction.getAmount();

                spending.put(
                        expenseType,
                        spending.getOrDefault(expenseType,0.0) + amount
                );
            }
        }
        return spending;
    }

    /*
    questions to answer:
    where does the income data currently live? - current no where as it is not saved anywhere
    what should getIncomeType return -> it should get a list of all income and its type

    task:
    implement the getIncomeType function.
    implement search for transaction function
    implement a database
     */

    public Map<IncomeType, Double> getIncomeByCategory() {
        Map<IncomeType, Double> income = new HashMap<>();
        for (Transaction transaction : transactions) {
            if(transaction.getType().equals(FilterType.INCOME))
            {
                IncomeType incomeType = transaction.getIncomeType();
                double amount = transaction.getAmount();
                income.put(incomeType,
                        income.getOrDefault(incomeType,0.0) + amount);
            }
        }
        return income;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transaction> getTransactionsByType(FilterType type){
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if(transaction.getType() == type) // ignore case sensitivity
            {
                filtered.add(transaction);
            }
        }
        return filtered;
    }

    public List<Transaction> getTransactionsBetweenDate(LocalDate startDate, LocalDate endDate){
        List<Transaction> filtered = new ArrayList<>();

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();
            if(!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate))
            {
                filtered.add(transaction);
            }
        }
        return filtered;
    }
}
