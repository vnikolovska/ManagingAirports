package com.codeit.airports.managingairports.service;

import com.codeit.airports.managingairports.model.Flight;
import com.codeit.airports.managingairports.model.dto.FlightDto;
import com.codeit.airports.managingairports.model.dto.FlightListDto;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FlightService {


    List<Flight> importFlightsCsv() throws IOException, CsvException;

    Optional<Flight> addFlight(Flight flight);

    Optional<Flight> addFlight(FlightDto flightDto);

    Optional<Flight> editFlight(Flight flight);

    Optional<Flight> editFlight(Long id, FlightDto flightDto) throws InvalidFlightException;

    FlightListDto getFlightsByAirportCode(String code);

    List<Flight> findDirectFlightsFromGivenAirport(String code);

    List<Flight> findDirectFlights(String startingCode, String destinationCode);

}
