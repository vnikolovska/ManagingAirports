package com.codeit.airports.managingairports.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/homepage")
@Controller
public class HomeController {
    @GetMapping
    public String viewHomePage() {
        return "home";
    }
}
