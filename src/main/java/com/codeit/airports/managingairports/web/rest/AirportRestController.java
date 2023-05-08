package com.codeit.airports.managingairports.web.rest;

import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.dto.AirportDto;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;
import com.codeit.airports.managingairports.service.AirportService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/airport")
public class AirportRestController {

    @Autowired
    private AirportService airportService;


    @GetMapping("/all")
    public List<Airport> getAirports() throws IOException, CsvException {
        return airportService.importCsv();

    }


    @PostMapping("/add")
    public ResponseEntity<Airport> addAirport(@RequestBody AirportDto airportDto) throws InvalidFlightException {
        return this.airportService.addAirport(airportDto)
                .map(airport -> ResponseEntity.ok().body(airport))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> deleteAirport(@PathVariable String code) throws InvalidFlightException {
        this.airportService.deleteAirport(code);
        return ResponseEntity.ok().body(code);


    }

    @PostMapping("/filter/{country}")
    public ResponseEntity<Airport> filterAirport(@PathVariable String country) {

        //proverka za ako e prazno da se napravi if else
        return this.airportService.findAirportWithMostPassengersByCountry(country)
                .map(airport -> ResponseEntity.ok().body(airport))
                .orElseGet(() -> ResponseEntity.badRequest().build());


    }

}
