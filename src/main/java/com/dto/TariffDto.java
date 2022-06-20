package com.dto;

import com.model.ServiceType;

import java.time.LocalDateTime;
import java.util.List;

public class TariffDto {
    private int id;
    private String name;
    private ServiceType type;
    private int pricePerDay;
    private boolean isActive;
    private LocalDateTime created;
    private LocalDateTime updated;

    public TariffDto() {
    }

    public TariffDto(String name, ServiceType type, int pricePerDay, boolean isActive, LocalDateTime created, LocalDateTime updated) {
        this.name = name;
        this.type = type;

        this.pricePerDay = pricePerDay;
        this.isActive = isActive;
        this.created = created;
        this.updated = updated;
    }

    public TariffDto(String name, ServiceType type, int pricePerDay) {
        this.name = name;
        this.type = type;
        this.pricePerDay = pricePerDay;
    }

    public TariffDto(int id, String name, ServiceType type, int pricePerDay, boolean isActive, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pricePerDay = pricePerDay;
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

    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
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

    @Override
    public String toString() {
        return "TariffDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", pricePerDay=" + pricePerDay +
                ", isActive=" + isActive +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
