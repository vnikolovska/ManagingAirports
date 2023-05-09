package com.codeit.airports.managingairports.web.rest;

import com.codeit.airports.managingairports.model.Flight;
import com.codeit.airports.managingairports.model.dto.FlightDto;
import com.codeit.airports.managingairports.model.dto.FlightListDto;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;
import com.codeit.airports.managingairports.service.Impl.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/flights")
@RestController
public class FlightRestController {

    @Autowired
    private FlightServiceImpl flightService;


    @PostMapping("/add")
    public ResponseEntity<Flight> addFlight(@RequestBody FlightDto flightDto) throws InvalidFlightException {
        return this.flightService.addFlight(flightDto)
                .map(flight -> ResponseEntity.ok().body(flight))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Flight> editFlight(@PathVariable Long id, @RequestBody FlightDto flightDto) throws InvalidFlightException {
        return this.flightService.editFlight(id, flightDto)
                .map(flight -> ResponseEntity.ok().body(flight))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/filter/{code}")
    public FlightListDto findFlightsByAirportCode(@PathVariable String code) {

        return this.flightService.getFlightsByAirportCode(code);


    }

    @PostMapping("/find/{code}")
    public List<Flight> findDirectFlightsByGivenAirport(@PathVariable String code) {


        return this.flightService.findDirectFlightsFromGivenAirport(code);


    }


    @PostMapping("/findDirectFlights/{startingCode}/{destinationCode}")
    public List<Flight> findDirectFlights(@PathVariable String startingCode, @PathVariable String destinationCode) {


        return this.flightService.findDirectFlights(startingCode, destinationCode);


    }
}
