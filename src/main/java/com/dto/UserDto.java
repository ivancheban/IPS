package com.dto;

import com.model.Role;

import java.time.LocalDateTime;

public class UserDto {

    private int id;
    private String phone;
    private String password;
    private boolean isActive;
    private Role role;
    private LocalDateTime created;
    private LocalDateTime updated;

    public UserDto() {
    }

    public UserDto(String phone, String password, boolean isActive, Role role, LocalDateTime created, LocalDateTime updated) {
        this.phone = phone;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
        this.created = created;
        this.updated = updated;
    }

    public UserDto(int id, String phone, String password, boolean isActive, Role role) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role =role;
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
        return "UserDto{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", role=" + role +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
