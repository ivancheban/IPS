package com.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SubscriptionDto {
    private int id;
    private String name;
    private int days;
    private boolean isActive;
    private LocalDateTime created;
    private LocalDateTime updated;

    public SubscriptionDto() {
    }

    public SubscriptionDto(String name, int days) {
        this.name = name;
        this.days = days;
    }

    public SubscriptionDto(String name, int days, boolean isActive, LocalDateTime created, LocalDateTime updated) {
        this.name = name;
        this.days = days;
        this.isActive = isActive;
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
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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
}
