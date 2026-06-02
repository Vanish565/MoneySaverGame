package com.moneysaver.game;

import com.moneysaver.controller.BudgetController;
import com.moneysaver.service.BudgetTracker;
import com.moneysaver.ui.ConsoleMenu;


public class Main {
    public static void main(String[] args) {
        // Service layer
        BudgetTracker tracker = new BudgetTracker();

        // Controller layer
        BudgetController budgetController = new BudgetController(tracker);

        // UI layer
        ConsoleMenu menu = new ConsoleMenu(budgetController);

        menu.start();
    }
}