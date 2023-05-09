package com.codeit.airports.managingairports.web;

import com.codeit.airports.managingairports.model.Flight;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;
import com.codeit.airports.managingairports.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping
public class FlightController {


    @Autowired
    FlightService flightService;

    @GetMapping("/viewFlights")
    public String viewAirports(Model model) {
        model.addAttribute("flights", flightService.listFlights());
        return "listFlight";
    }

    @GetMapping("/newFlight")
    public String newFlight(Model model) {
        Flight flight = new Flight();
        model.addAttribute("flight", flight);
        return "addFlight";
    }

    @PostMapping("/saveFlight")
    public String saveFlight(@ModelAttribute("flight") Flight flight, Model model, @RequestParam(required = false) Long id) {

        try {
            if (id != null) {
                flightService.editFlight(id, flight);
            } else {
                flightService.addFlight(flight);

            }
            return "redirect:/viewFlights";

        } catch (InvalidFlightException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "redirect:/addFlight?error=" + e.getMessage();
        }


    }


    @PutMapping("/editFlight/{id}")
    public String editFlight(@PathVariable(value = "id") Long id, Model model) {
        Optional<Flight> flight = flightService.findFlightById(id);

        model.addAttribute("flight", flight.get());
        return "editFlight";
    }

}
