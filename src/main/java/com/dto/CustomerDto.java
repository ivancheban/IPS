package com.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerDto {
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private List<SubscriptionDto> servicesDto;
    private boolean isActive;
    private LocalDateTime created;
    private LocalDateTime updated;
    private int balance;

    public CustomerDto(CustomerDto customerDto) {
    }

    public CustomerDto(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public CustomerDto(int id, String name, String surname, String phone, String email,List<SubscriptionDto> servicesDto, boolean isActive, LocalDateTime created, LocalDateTime updated, int balance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.servicesDto=servicesDto;
        this.isActive = isActive;
        this.created = created;
        this.updated = updated;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBalance() {return balance;}

    public void setBalance(int balance) {this.balance = balance;}

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

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", isActive=" + isActive +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
