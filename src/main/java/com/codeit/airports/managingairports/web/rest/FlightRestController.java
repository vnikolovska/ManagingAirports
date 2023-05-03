package com.codeit.airports.managingairports.web.rest;

import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.Flight;
import com.codeit.airports.managingairports.service.Impl.AirportServiceImpl;
import com.codeit.airports.managingairports.service.Impl.FlightServiceIml;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FlightRestController {

    @Autowired
    private FlightServiceIml flightService;


    @GetMapping("/flights")
    public List<Flight> getAirports() throws IOException, CsvException {
        return flightService.importFlightsCsv();

    }
}
