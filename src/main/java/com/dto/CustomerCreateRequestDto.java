package com.dto;

public class CustomerCreateRequestDto {
    private  int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private  String confirm_password;

    public CustomerCreateRequestDto() {
    }

    public CustomerCreateRequestDto(int id, String name, String surname, String email, String phone, String password, String confirm_password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.confirm_password = confirm_password;
    }

    public CustomerCreateRequestDto(String name, String surname, String email, String phone, String password, String confirm_password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.confirm_password = confirm_password;
    }

    public CustomerCreateRequestDto(int id, String name, String surname, String email, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

}
