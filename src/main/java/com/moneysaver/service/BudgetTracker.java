package com.moneysaver.service;

import com.moneysaver.model.Category;
import com.moneysaver.model.IncomeType;
import com.moneysaver.model.Transaction;
import com.moneysaver.repository.TransactionRepository;

import java.util.*;

public class BudgetTracker {
//    private List<Transaction> transactions =  new ArrayList<>();

    private TransactionRepository repository;
    private List<Transaction> transactions;

    // constructor
    public BudgetTracker(TransactionRepository repository) {
        this.repository = repository;
        this.transactions = repository.loadTransactions();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        repository.saveTransactions(transactions);
    }

    public double getTotalIncome(){
        double total = 0;
        for(Transaction transaction : transactions){
            if(transaction.getType().equals("income"))
            {
                total += transaction.getAmount();
            }
        }
        return total;
    }

    public double getTotalExpenses() {
        double total = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType().equals("expense")) {
                total += transaction.getAmount();
            }
        }
        return total;
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpenses();
    }

    public Map<Category, Double> getSpendingByCategory() {
        Map<Category, Double> spending = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getType().equals("expense"))
            {
                Category category = transaction.getCategory();
                double amount = transaction.getAmount();

                spending.put(
                        category,
                        spending.getOrDefault(category,0.0) + amount
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
            if(transaction.getType().equals("income"))
            {
                IncomeType incomeType = transaction.getIncomeType();
                double amount = transaction.getAmount();
                income.put(incomeType,
                        income.getOrDefault(incomeType,0.0) + amount);
            }
        }
        return income;
    }
}
