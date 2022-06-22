package com.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDto {
    private int id;
    private String name;
    private int days_amount;
    private boolean isActive;
    private List<TariffDto> tariffs = new ArrayList<>();
    private LocalDateTime created;
    private LocalDateTime updated;

    public SubscriptionDto() {
    }

    public SubscriptionDto(String name, int days) {
        this.name = name;
        this.days_amount = days;
    }


    public SubscriptionDto(int id, String name, int days_amount) {
        this.id = id;
        this.name = name;
        this.days_amount = days_amount;

    }

    public SubscriptionDto(int id, String name, int days_amount, boolean isActive) {
        this.id = id;
        this.name = name;
        this.days_amount = days_amount;
        this.isActive = isActive;
    }

    public SubscriptionDto(String name) {
        this.name = name;
    }


    public SubscriptionDto(int id, String name, int days_amount, boolean isActive, LocalDateTime created, LocalDateTime updated, List<TariffDto> tariffs) {
        this.id = id;
        this.name = name;
        this.days_amount = days_amount;
        this.isActive = isActive;
        this.tariffs = tariffs;
        this.created = created;
        this.updated = updated;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDays() {
        return days_amount;
    }

    public void setDays(int days) {
        this.days_amount = days;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getCreated() {
        return created.toLocalDate();
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated.toLocalDate();
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDays_amount() {
        return days_amount;
    }

    public void setDays_amount(int days_amount) {
        this.days_amount = days_amount;
    }

    public List<TariffDto> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<TariffDto> tariffs) {
        this.tariffs = tariffs;
    }


    @Override
    public String toString() {

            return "Subscription{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", days_amount=" + days_amount +
                    ", isActive=" + isActive +
                    ", created=" + created +
                    ", updated=" + updated +
                    '}';
        }
    }

