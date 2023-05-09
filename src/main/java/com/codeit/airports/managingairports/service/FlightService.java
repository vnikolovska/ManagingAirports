package com.codeit.airports.managingairports.service;

import com.codeit.airports.managingairports.model.Flight;
import com.codeit.airports.managingairports.model.dto.FlightDto;
import com.codeit.airports.managingairports.model.dto.FlightListDto;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<Flight> listFlights();


    Optional<Flight> addFlight(Flight flight) throws InvalidFlightException;

    Optional<Flight> addFlight(FlightDto flightDto) throws InvalidFlightException;

    Optional<Flight> editFlight(Long id, Flight flight) throws InvalidFlightException;

    Optional<Flight> editFlight(Long id, FlightDto flightDto) throws InvalidFlightException;

    FlightListDto getFlightsByAirportCode(String code);

    List<Flight> findDirectFlightsFromGivenAirport(String code);

    List<Flight> findDirectFlights(String startingCode, String destinationCode);

    Optional<Flight> findFlightById(Long id);

}
