package com.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDto {
    private int id;
    private String name;
    private boolean isActive;
    private List<TariffDto> tariffs = new ArrayList<>();
    private LocalDateTime created;
    private LocalDateTime updated;

    public SubscriptionDto() {
    }




    public SubscriptionDto(int id, String name,boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;

    }

    public SubscriptionDto(int id, String name, boolean isActive, LocalDateTime created,LocalDateTime updated) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;

    }

    public SubscriptionDto(String name) {
        this.name = name;
    }


    public SubscriptionDto(int id, String name, boolean isActive, LocalDateTime created, LocalDateTime updated, List<TariffDto> tariffs) {
        this.id = id;
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                ", isActive=" + isActive +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}

