package com.moneysaver.service;

import com.moneysaver.model.Category;
import com.moneysaver.model.Transaction;
import java.util.*;

public class BudgetTracker {
    private List<Transaction> transactions =  new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
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
}
