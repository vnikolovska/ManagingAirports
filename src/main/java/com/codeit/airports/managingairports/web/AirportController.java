package com.codeit.airports.managingairports.web;


import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;
import com.codeit.airports.managingairports.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping("/viewAirports")
    public String viewAirports(Model model) {
        model.addAttribute("airports", airportService.listAirports());
        return "listAirports";
    }

    @GetMapping("/addAirports")
    public String addAirport(Model model) {
        Airport airport = new Airport();
        model.addAttribute("airport", airport);
        return "addAirport";
    }

    @PostMapping("/saveAirport")
    public String saveAirport(@ModelAttribute("airport") Airport airport, Model model) {

        try {
            airportService.addAirport(airport);
            return "redirect:/viewAirports";
        } catch (InvalidFlightException ex) {
            model.addAttribute("error", ex.getMessage());
            return "redirect:/addAirport?error=" + ex.getMessage();
        }
    }


    @DeleteMapping("/delete/{code}")

    public String deleteAirport(@PathVariable(value = "code") String code, Model model) {
        try {
            this.airportService.deleteAirport(code);
        } catch (InvalidFlightException ex) {
            model.addAttribute("error", ex.getMessage());
            return "redirect:/viewAirports?error=" + ex.getMessage();
        }

        return "redirect:/viewAirports";
    }

}



