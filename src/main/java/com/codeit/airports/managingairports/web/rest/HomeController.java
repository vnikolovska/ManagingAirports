package com.codeit.airports.managingairports.web.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/homepage")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> viewHomePage() {
        return ResponseEntity.ok().body("Success");
    }
}
