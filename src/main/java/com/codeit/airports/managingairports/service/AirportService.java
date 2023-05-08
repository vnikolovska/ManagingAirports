package com.codeit.airports.managingairports.service;

import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.dto.AirportDto;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AirportService {

    Optional<Airport> addAirport(Airport airport);

    Optional<Airport> addAirport(AirportDto airportDto) throws InvalidFlightException;

    List<Airport> importCsv() throws IOException, CsvException;

    void deleteAirport(String name) throws InvalidFlightException;

    Optional<Airport> findAirportWithMostPassengersByCountry(String country);
}
