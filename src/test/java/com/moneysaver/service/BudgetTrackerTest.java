package com.moneysaver.service;

import com.moneysaver.model.ExpenseType;
import com.moneysaver.model.IncomeType;
import com.moneysaver.model.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetTrackerTest {
    @Test
    void getTotalIncomeTest()
    {
        BudgetTracker tracker = new BudgetTracker();
        // Arrange
        tracker.addTransaction(new Transaction(1000, IncomeType.SALARY));
        tracker.addTransaction(new Transaction(2000, IncomeType.SALARY));
        // Act
        double totalIncome = tracker.getTotalIncome();

        // Assert
        assertEquals(3000,totalIncome,0);
    }

    @Test
    void getDaysUntilBrokeReturnsZeroWhenBalanceIsEmpty()
    {
        BudgetTracker tracker = new BudgetTracker();
        tracker.addTransaction(new Transaction(100, IncomeType.SALARY));
        tracker.addTransaction(new Transaction(100, ExpenseType.FOOD));

        int daysUntilBroke = tracker.getDaysUntilBroke();

        assertEquals(0, daysUntilBroke);
    }

    @Test
    void getDaysUntilBrokeUsesBalanceAndAverageDailyExpenses()
    {
        BudgetTracker tracker = new BudgetTracker();
        tracker.addTransaction(new Transaction(1000, IncomeType.SALARY));
        tracker.addTransaction(new Transaction(250, ExpenseType.FOOD));

        int daysUntilBroke = tracker.getDaysUntilBroke();

        assertEquals(3, daysUntilBroke);
    }

    @Test
    void getDaysUntilBrokeReturnsNegativeOneWhenThereAreNoExpenses()
    {
        BudgetTracker tracker = new BudgetTracker();
        tracker.addTransaction(new Transaction(1000, IncomeType.SALARY));

        int daysUntilBroke = tracker.getDaysUntilBroke();

        assertEquals(-1, daysUntilBroke);
    }

    @Test
    void getExpenseRateReturnsAverageDailyExpenses()
    {
        BudgetTracker tracker = new BudgetTracker();
        tracker.addTransaction(new Transaction(500, ExpenseType.FOOD));

        double expenseRate = tracker.getExpenseRate();

        assertEquals(500, expenseRate, 0);
    }

}
