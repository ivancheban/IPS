package com.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Tariff {
    private int id;
    private String name;
    private ServiceType type;
    private int pricePerDay;
    private boolean isActive;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Tariff() {
    }

    public Tariff(String name, ServiceType type, int pricePerDay) {
        this.name = name;
        this.type = type;
        this.pricePerDay = pricePerDay;
    }

    public Tariff(String name, ServiceType type, int pricePerDay, boolean isActive, LocalDateTime created, LocalDateTime updated) {
        this.name = name;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.isActive = isActive;
        this.created = created;
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", pricePerDay=" + pricePerDay +
                ", isActive=" + isActive +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    public Tariff(int id, String name, ServiceType type, int pricePerDay, boolean isActive, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.name = name;
        this.type = type;

        this.pricePerDay = pricePerDay;
        this.isActive = isActive;
        this.created = created;
        this.updated = updated;
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

    public Timestamp convertToTimestamp(LocalDateTime date){
        if(date == null) date = LocalDateTime.now();
        return Timestamp.valueOf(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tariff)) return false;
        Tariff tariff = (Tariff) o;
        return Objects.equals(name, tariff.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
