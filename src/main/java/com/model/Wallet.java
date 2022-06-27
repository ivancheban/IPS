package com.model;

public class Wallet {
    private int id;
    private String number;
    private double balance;
    private int customerId;


    public Wallet() {
    }

    public Wallet(int id,String number, double balance, int customerId) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", customerId=" + customerId +
                '}';
    }
}
