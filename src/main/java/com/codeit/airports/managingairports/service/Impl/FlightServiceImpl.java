package com.codeit.airports.managingairports.service.Impl;

import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.Flight;
import com.codeit.airports.managingairports.model.dto.FlightDto;
import com.codeit.airports.managingairports.model.dto.FlightListDto;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;
import com.codeit.airports.managingairports.repository.AirportRepository;
import com.codeit.airports.managingairports.repository.FlightRepository;
import com.codeit.airports.managingairports.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {


    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;


    @Override
    public List<Flight> listFlights() {
        return this.flightRepository.findAll();
    }

    @Override
    public Optional<Flight> addFlight(Flight flight) throws InvalidFlightException {

        if (flight.getFlightDuration() >= 0 && flight.getDepartureTime() > 0) {
            Flight flight1 = new Flight(flight.getCodeOfStartingAirport(), flight.getCodeOfDestinationAirport(), flight.getDepartureTime(),
                    flight.getFlightDuration());

            return Optional.of(this.flightRepository.save(flight1));
        } else throw new InvalidFlightException("Invalid Arguments");
    }

    @Override
    public Optional<Flight> addFlight(FlightDto flightDto) throws InvalidFlightException {
        Optional<Airport> startingAirport = this.airportRepository.findByAirportCode(flightDto.getCodeOfStartingAirport());
        Optional<Airport> destinationAirport = this.airportRepository.findByAirportCode(flightDto.getCodeOfDestinationAirport());
        if (flightDto.getFlightDuration() >= 0 && flightDto.getDepartureTime() > 0) {
            Flight flight = new Flight(startingAirport.get(), destinationAirport.get(),
                    flightDto.getDepartureTime(),
                    flightDto.getFlightDuration());

            return Optional.of(this.flightRepository.save(flight));
        } else throw new InvalidFlightException("Invalid Arguments");
    }

    @Override
    public Optional<Flight> editFlight(Long id, Flight flight) throws InvalidFlightException {
        if (this.flightRepository.findById(id).isPresent()) {

            Optional<Flight> flight1 = this.flightRepository.findById(flight.getId());
            flight1.get().setFlightDuration(flight.getFlightDuration());
            flight1.get().setDepartureTime(flight.getDepartureTime());
            flight1.get().setCodeOfDestinationAirport(flight.getCodeOfDestinationAirport());
            flight1.get().setCodeOfStartingAirport(flight.getCodeOfStartingAirport());
            return Optional.of(this.flightRepository.save(flight1.get()));
        } else throw new InvalidFlightException("Invalid Flight");
    }


    @Override
    public Optional<Flight> editFlight(Long id, FlightDto flightDto) throws InvalidFlightException {
        if (this.flightRepository.findById(id).isPresent()) {
            Optional<Airport> startingAirport = airportRepository.findByAirportCode(flightDto.getCodeOfStartingAirport());
            Optional<Airport> destinationAirport = airportRepository.findByAirportCode(flightDto.getCodeOfDestinationAirport());

            Optional<Flight> flight = this.flightRepository.findById(id);
            flight.get().setFlightDuration(flightDto.getFlightDuration());
            flight.get().setDepartureTime(flightDto.getDepartureTime());
            flight.get().setCodeOfDestinationAirport(destinationAirport.get());
            flight.get().setCodeOfStartingAirport(startingAirport.get());
            return Optional.of(this.flightRepository.save(flight.get()));
        } else throw new InvalidFlightException("Invalid Flight");
    }


    @Override
    public FlightListDto getFlightsByAirportCode(String code) {
        Optional<Airport> airport = this.airportRepository.findByAirportCode(code);
        String airportName = airport.get().getAirportName();


        List<Flight> flights = flightRepository.findAll();
        List<Flight> filteredFlights = new ArrayList<>(flights.stream().filter(flight -> flight.getCodeOfStartingAirport().getAirportCode().equals(code)).toList());

        Comparator<Flight> destinationAirportCodeComparator = Comparator.comparing(Flight::getCodeOfDestinationAirport);
        Comparator<Flight> departureTimeComparator = Comparator.comparing(Flight::getDepartureTime);
        filteredFlights.sort(destinationAirportCodeComparator.thenComparing(departureTimeComparator));


        return new FlightListDto(airportName, filteredFlights);


    }


    @Override
    public List<Flight> findDirectFlightsFromGivenAirport(String code) {
        return this.flightRepository.findDirectFlightsForSpecificAirport(code);
    }


    @Override
    public List<Flight> findDirectFlights(String startingCode, String destinationCode) {
        return this.flightRepository.findDirectFLights(startingCode, destinationCode);
    }

    @Override
    public Optional<Flight> findFlightById(Long id) {
        return this.flightRepository.findById(id);
    }

}
