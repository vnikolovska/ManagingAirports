package com.codeit.airports.managingairports.service;

import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.dto.AirportDto;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;

import java.util.List;
import java.util.Optional;

public interface AirportService {

    List<Airport> listAirports();

    Optional<Airport> addAirport(Airport airport) throws InvalidFlightException;

    Optional<Airport> addAirport(AirportDto airportDto) throws InvalidFlightException;


    void deleteAirport(String name) throws InvalidFlightException;

    Optional<Airport> findAirportWithMostPassengersByCountry(String country);
}
