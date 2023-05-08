package com.codeit.airports.managingairports.model.dto;


import lombok.Data;

@Data
public class UserRegisterDto {

    String username;
    String password;
    String repeatPassword;
    String role;

    public UserRegisterDto(String username, String password, String repeatPassword, String role) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.role = role;
    }
}
