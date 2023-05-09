package com.codeit.airports.managingairports.model.dto;


import com.codeit.airports.managingairports.model.User;
import com.codeit.airports.managingairports.model.enumerations.Role;
import lombok.Data;


@Data
public class UserDetailsDto {
    private String username;

    private String password;

    private Role role;


    public static UserDetailsDto of(User user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.password = user.getPassword();
        details.role = user.getRole();
        return details;
    }
}
