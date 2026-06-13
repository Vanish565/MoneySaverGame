package com.moneysaver.service;

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

}
