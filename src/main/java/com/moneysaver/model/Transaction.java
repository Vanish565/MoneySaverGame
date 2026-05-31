package com.moneysaver.model;

public class Transaction {

    private String type;
    private double amount;
    private String category;

    public Transaction(String type, double amount, String category) {
        this.type = type;
        this.amount = amount;
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }
}