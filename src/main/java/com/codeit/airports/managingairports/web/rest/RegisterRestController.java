package com.codeit.airports.managingairports.web.rest;

import com.codeit.airports.managingairports.model.User;
import com.codeit.airports.managingairports.model.dto.UserRegisterDto;
import com.codeit.airports.managingairports.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterRestController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> register(@RequestBody UserRegisterDto userRegisterDto) throws Exception {

        return ResponseEntity.ok(userService.register(userRegisterDto));
    }
}