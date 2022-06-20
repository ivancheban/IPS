package com.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Limit {
    private int id;
    private String name;
    private int amount;
    private boolean isActive;
    private LocalDateTime created;
    private LocalDateTime updated;
    private int tariff_id;

    public Limit() {
    }

    public Limit(int id, String name, int amount, boolean isActive, LocalDateTime created, LocalDateTime updated, int tariff_id) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.isActive = isActive;
        this.created = created;
        this.updated = updated;
        this.tariff_id = tariff_id;
    }

    public Limit(String name, int amount, boolean isActive, LocalDateTime created, LocalDateTime updated) {
        this.name = name;
        this.amount = amount;
        this.isActive = isActive;
        this.created = created;
        this.updated = updated;
    }

    public Timestamp convertToTimestamp(LocalDateTime date){
        if(date == null) date = LocalDateTime.now();
        return Timestamp.valueOf(date);
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public int getTariff_id() {
        return tariff_id;
    }

    public void setTariff_id(int tariff_id) {
        this.tariff_id = tariff_id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
