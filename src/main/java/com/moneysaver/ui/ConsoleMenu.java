package com.moneysaver.ui;

import com.moneysaver.model.Transaction;
import com.moneysaver.service.BudgetTracker;

import java.util.Scanner;

public class ConsoleMenu {
    private BudgetTracker tracker;
    private Scanner scanner;

    public ConsoleMenu(BudgetTracker tracker) {
        this.tracker = tracker;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n=== MONEY SAVER GAME ===");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Stats");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addIncome();
                case 2 -> addExpense();
                case 3 -> showStats();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void addIncome() {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        tracker.addTransaction(
                new Transaction("income", amount, "salary")
        );
    }

    private void addExpense() {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        System.out.print("Enter category: ");
        String category = scanner.next();

        tracker.addTransaction(
                new Transaction("expense", amount, category)
        );
    }

    private void showStats() {
        System.out.println("Income: " + tracker.getTotalIncome());
        System.out.println("Expenses: " + tracker.getTotalExpenses());
        System.out.println("Savings: " + tracker.getSavings());
    }

}
