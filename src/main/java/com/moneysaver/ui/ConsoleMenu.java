package com.moneysaver.ui;

import com.moneysaver.controller.BudgetController;
import com.moneysaver.model.Category;
import com.moneysaver.model.IncomeType;

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
            System.out.println("3. View Stats");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addIncome();
                case 2 -> addExpense();
                case 3 -> showStats();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }

    private void addIncome() {
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();

        System.out.println("\nSelect income type:");

        IncomeType[] incomeType = IncomeType.values();

        for(int i =0; i < incomeType.length; i++){
            System.out.println((i+1) + ". " + incomeType[i]);
        }

        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        IncomeType selectedIncomeType = incomeType[choice-1];

        budgetController.addIncome(amount,  selectedIncomeType);

        System.out.println("Income added under: " + selectedIncomeType);
    }

    private void addExpense() {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        System.out.println("\nSelect category:");

        Category[] categories = Category.values();

        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }

        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        Category selectedCategory = categories[choice - 1];

        budgetController.addExpense(amount, selectedCategory);

        System.out.println("Expense added under: " + selectedCategory);
    }

    private void showStats() {
        System.out.println("Income: " + budgetController.getIncome());
        System.out.println("Expenses: " + budgetController.getExpenses());
        System.out.println("Balance: " + budgetController.getBalance());
    }
}