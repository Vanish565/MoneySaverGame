package com.moneysaver.game;

import com.moneysaver.controller.BudgetController;
import com.moneysaver.repository.TransactionRepository;
import com.moneysaver.service.BudgetTracker;
import com.moneysaver.ui.ConsoleMenu;


public class Main {

    public static void main(String[] args) {

        try {
            // 1. Repository (persistence layer)
            TransactionRepository repository = new TransactionRepository("transactions.json");

            // 2. Service layer (business logic)
            BudgetTracker tracker = new BudgetTracker(repository);

            // 3. Controller layer (request handling)
            BudgetController controller = new BudgetController(tracker);

            // 4. UI layer
            ConsoleMenu menu = new ConsoleMenu(controller);

            // 5. Start application
            menu.start();

        } catch (Exception e) {
            System.out.println("Fatal error starting application:");
            e.printStackTrace();
        }
    }
}