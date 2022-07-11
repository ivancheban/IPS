package com.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private List<Tariff> tariffs;
    private boolean isActive;
    private LocalDateTime created;
    private LocalDateTime updated;
    private int balance;

    public Customer() {

    }


    public Customer(String name, String surname, String phone, String email) {

        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;

    }

    public Customer(int id, String name, String surname, String phone, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public Customer(int id, String name, String surname, String phone, String email, List<Tariff> tariffs, boolean isActive, LocalDateTime created, LocalDateTime updated, int balance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.tariffs = tariffs;
        this.isActive = isActive;
        this.created = created;
        this.updated = updated;
        this.balance = balance;
    }

    public Customer(int id, String name, String surname, String phone, String email, int balance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.balance = balance;
    }

    public Customer(String name, String surname, String phone, String email, int balance) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Timestamp convertToTimestamp(LocalDateTime date) {
        if (date == null) date = LocalDateTime.now();
        return Timestamp.valueOf(date);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", tariffs=" + tariffs +
                ", isActive=" + isActive +
                ", created=" + created +
                ", updated=" + updated +
                ", balance=" + balance +
                '}';
    }
}
