package com.codeit.airports.managingairports.web.rest;


import com.codeit.airports.managingairports.config.filters.JwtAuthenticationFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/login")
@RestController
public class LoginRestController {


    @Autowired
    private JwtAuthenticationFilter filter;


    @PostMapping
    public ResponseEntity<String> Login(HttpServletRequest request,
                                        HttpServletResponse response) throws JsonProcessingException {


        Authentication auth = this.filter.attemptAuthentication(request, response);
        if (auth != null) {
            return ResponseEntity.ok(this.filter.generateJwt(response, auth));
        } else
            return ResponseEntity.badRequest().body("Bad request");

    }
}
