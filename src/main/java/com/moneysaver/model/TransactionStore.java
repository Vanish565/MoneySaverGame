package com.moneysaver.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionStore {
    private List<Transaction> transactions;

    // required for GSON
    public TransactionStore() {
        this.transactions = new ArrayList<>();
    }

    public TransactionStore(List<Transaction> transactions) {
        if(transactions == null)
        {
            this.transactions = new ArrayList<>();
        }
        else {
            this.transactions = transactions;
        }
    }

    // getters and setters

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
