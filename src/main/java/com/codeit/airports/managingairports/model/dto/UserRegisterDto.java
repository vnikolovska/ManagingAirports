package com.codeit.airports.managingairports.model.dto;


import com.codeit.airports.managingairports.model.enumerations.Role;
import lombok.Data;

@Data
public class UserRegisterDto {

    String username;
    String password;
    String repeatPassword;
    Role role;

    public UserRegisterDto(String username, String password, String repeatPassword, Role role) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.role = role;
    }
}
