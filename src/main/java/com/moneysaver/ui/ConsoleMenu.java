package com.moneysaver.ui;


import com.moneysaver.controller.BudgetController;
import com.moneysaver.model.Category;
import com.moneysaver.model.IncomeType;
import com.moneysaver.model.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleMenu {

    private BudgetController budgetController;
    private Scanner scanner;

    public ConsoleMenu(BudgetController budgetController) {
        this.budgetController = budgetController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n=== MONEY SAVER GAME ===");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Details");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int choice = getValidMenuChoice(1,4);

            switch (choice) {
                case 1 -> addIncome();
                case 2 -> addExpense();
                case 3 -> showDetails();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addIncome() {
        double amount = getValidAmount();

        System.out.println("\nSelect income type:");

        IncomeType[] incomeType = IncomeType.values();

        for(int i =0; i < incomeType.length; i++){
            System.out.println((i+1) + ". " + incomeType[i]);
        }

        System.out.print("Choice: ");
        int choice = getValidMenuChoice(1, incomeType.length);

        IncomeType selectedIncomeType = incomeType[choice-1];

        budgetController.addIncome(amount,  selectedIncomeType);

        System.out.println("Income recorded: " + selectedIncomeType + " | R" + amount);
    }

    private void addExpense() {
        double amount = getValidAmount();

        System.out.println("\nSelect category:");

        Category[] categories = Category.values();

        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }

        System.out.print("Choice: ");
        int choice = getValidMenuChoice(1, categories.length);

        Category selectedCategory = categories[choice - 1];

        budgetController.addExpense(amount, selectedCategory);

        System.out.println("Expense recorded: " + selectedCategory + " | R" + amount);
    }

    private void showDetails(){
        while (true) {
            System.out.println("\n=== View Details ===");
            System.out.println("1. Summary");
            System.out.println("2. Income Breakdown");
            System.out.println("3. Spending Breakdown");
            System.out.println("4. Transaction History");
            System.out.println("5. Back");
            System.out.print("Choose: ");

            int choice = getValidMenuChoice(1, 5);

            switch (choice) {
                case 1 -> showStats();
                case 2 -> showIncomeByCategory();
                case 3 -> showSpendingByCategory();
                case 4 -> showTransactionHistory();
                case 5 -> {return;}
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void showStats() {
        System.out.println("Income: " + budgetController.getIncome());
        System.out.println("Expenses: " + budgetController.getExpenses());
        System.out.println("Balance: " + budgetController.getBalance());
    }

    private void showSpendingByCategory() {
        Map<Category, Double> spending = budgetController.getSpendingByCategory();

        System.out.println("\n=== Spending By Category ===");
        System.out.printf("%-15s %s%n",
                "EXPENSE TYPE", "AMOUNT");
        System.out.println("-----------------------------");

        for (Map.Entry<Category, Double> entry : spending.entrySet()) {
            System.out.printf("%-15s R %.2f%n", entry.getKey(), entry.getValue());
        }
    }

    // displays the income by its type
    private void showIncomeByCategory() {
        Map<IncomeType, Double> income = budgetController.getIncomeByCategory();
        System.out.println("\n=== Income By Category ===");
        System.out.printf("%-15s %s%n",
                "INCOME TYPE", "AMOUNT");
        System.out.println("-----------------------------");

        for (Map.Entry<IncomeType, Double> entry : income.entrySet()) {
            System.out.printf("%-15s R %.2f%n", entry.getKey(), entry.getValue());
        }
    }

    private void showTransactionHistory() {
        List<Transaction> transactions = budgetController.getTransactions();
        System.out.println("\n=== TRANSACTION HISTORY ===");
        System.out.printf("%-12s %-10s %-18s %s%n",
                "DATE", "TYPE", "CATEGORY/INCOME", "AMOUNT");
        System.out.println("------------------------------------------------");

        for (Transaction t : transactions) {

            String date = t.getDate().toString();
            String type = t.getType().toUpperCase();

            String categoryOrIncome;

            if (t.getType().equals("expense")) {
                categoryOrIncome = String.valueOf(t.getCategory());
            } else {
                categoryOrIncome = String.valueOf(t.getIncomeType());
            }

            System.out.printf(
                    "%-12s %-10s %-18s R %.2f%n",
                    date,
                    type,
                    categoryOrIncome,
                    t.getAmount()
            );
        }
    }

    // method to check whether the amount enter is valid
    // no letters, no negative numbers, no special characters
    private double getValidAmount() {

        while (true) {

            System.out.print("Enter amount: ");

            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid amount. Please enter a number.");
                scanner.next(); // consume the invalid input
                continue;
            }

            double amount = scanner.nextDouble();

            if (amount <= 0) {
                System.out.println("Amount must be greater than 0.");
                continue;
            }

            return amount;
        }
    }


    // checks that the choices is valid
    private int getValidMenuChoice(int min, int max) {

        while (true) {

            System.out.print("Choose: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid menu option.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            if (choice < min || choice > max) {
                System.out.println("Please choose between " + min + " and " + max + ".");
                continue;
            }

            return choice;
        }
    }

}