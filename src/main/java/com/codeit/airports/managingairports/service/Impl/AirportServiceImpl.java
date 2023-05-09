package com.codeit.airports.managingairports.service.Impl;


import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.Flight;
import com.codeit.airports.managingairports.model.dto.AirportDto;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;
import com.codeit.airports.managingairports.repository.AirportRepository;
import com.codeit.airports.managingairports.repository.FlightRepository;
import com.codeit.airports.managingairports.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private FlightRepository flightRepository;


    @Override
    public List<Airport> listAirports() {
        return this.airportRepository.findAll();
    }

    @Override
    public Optional<Airport> addAirport(Airport airport) throws InvalidFlightException {
        if (this.airportRepository.findByAirportCode(airport.getAirportCode()).isPresent()) {
            throw new InvalidFlightException("The airport already exists !");

        }
        Airport air = new Airport(airport.getAirportName(), airport.getCountryName(),
                airport.getAirportCode(), airport.getNumberOfAnnuallyPassengers());
        return Optional.of(this.airportRepository.save(air));
    }

    @Override
    public Optional<Airport> addAirport(AirportDto airportDto) throws InvalidFlightException {
        if (this.airportRepository.findByAirportCode(airportDto.getAirportCode()).isPresent()) {
            throw new InvalidFlightException("The airport already exists !");
        }
        Airport airport = new Airport(airportDto.getAirportName(), airportDto.getCountryName(),
                airportDto.getAirportCode(), airportDto.getNumberOfAnnuallyPassengers());
        return Optional.of(this.airportRepository.save(airport));
    }


    @Override
    public void deleteAirport(String code) throws InvalidFlightException {
        if (this.airportRepository.findByAirportCode(code).isPresent()) {
            Optional<Airport> airport = this.airportRepository.findByAirportCode(code);
            List<Flight> startingAirport = this.flightRepository.findByCodeOfStartingAirport(airport.get());
            List<Flight> destinationAirport = this.flightRepository.findByCodeOfDestinationAirport(airport.get());
            this.flightRepository.deleteAll(startingAirport);
            this.flightRepository.deleteAll(destinationAirport);


            this.airportRepository.delete(airport.get());


        } else throw new InvalidFlightException("Invalid airport");
    }


    @Override
    public Optional<Airport> findAirportWithMostPassengersByCountry(String country) {
        return this.airportRepository.findMaxNumberAnnuallyPassengersByCountry(country);


    }


}

