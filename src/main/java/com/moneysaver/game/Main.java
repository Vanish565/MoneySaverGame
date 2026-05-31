package com.moneysaver.game;

import com.moneysaver.model.Transaction;
import com.moneysaver.service.BudgetTracker;
import com.moneysaver.ui.ConsoleMenu;

public class Main {
    public static void main(String[] args) {

//        BudgetTracker tracker = new BudgetTracker();
//
//        tracker.addTransaction(new Transaction("income", 5000, "salary"));
//        tracker.addTransaction(new Transaction("expense", 1200, "food"));
//        tracker.addTransaction(new Transaction("expense", 800, "transport"));
//
//        System.out.println("Income: " + tracker.getTotalIncome());
//        System.out.println("Expenses: " + tracker.getTotalExpenses());
//        System.out.println("Savings: " + tracker.getSavings());

        BudgetTracker tracker = new BudgetTracker();
        ConsoleMenu consoleMenu = new ConsoleMenu(tracker);

        consoleMenu.start();
    }
}