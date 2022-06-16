package com.dto;

public class WalletDto {
    private int id;
    private String number;
    private double balance;
    private int customerId;

    public WalletDto() {
    }

    public WalletDto(String number, double balance) {
        this.number = number;
        this.balance = balance;

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
}
