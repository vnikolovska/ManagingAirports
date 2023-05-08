package com.codeit.airports.managingairports.model.dto;


import com.codeit.airports.managingairports.model.User;
import com.codeit.airports.managingairports.model.enumerations.Role;
import lombok.Data;


@Data
public class UserDetailsDto {
    private String username;

    private String password;

    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


//private Role role;


    public static UserDetailsDto of(User user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.password = user.getPassword();
        details.role = user.getRole();
        return details;
    }
}
