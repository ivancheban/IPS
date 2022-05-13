package com.dto;

public class UserCreateRequestDto {
    private String phone;
    private String password;
    private  String confirm_password;

    public UserCreateRequestDto(String phone, String password, String condPassword) {
        this.phone = phone;
        this.password = password;
        this.confirm_password = confirm_password;
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
