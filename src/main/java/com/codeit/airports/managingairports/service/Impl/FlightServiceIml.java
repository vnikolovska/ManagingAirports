package com.codeit.airports.managingairports.service.Impl;

import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.Flight;
import com.codeit.airports.managingairports.model.dto.FlightDto;
import com.codeit.airports.managingairports.model.dto.FlightListDto;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;
import com.codeit.airports.managingairports.repository.AirportRepository;
import com.codeit.airports.managingairports.repository.FlightRepository;
import com.codeit.airports.managingairports.service.FlightService;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceIml implements FlightService {


    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public List<Flight> importFlightsCsv() throws IOException, CsvException {
        Reader reader = new FileReader("src/main/resources/data/flights.csv");
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();


        List<String[]> rows = csvReader.readAll();
        List<Flight> flights = new ArrayList<>();

        for (String[] row : rows) {


            Flight flight = new Flight(row[0], row[1], Integer.parseInt(row[2]), Integer.parseInt(row[3]));

            flights.add(flight);
            flightRepository.save(flight);

        }
        return flights;
    }

    @Override
    public Optional<Flight> addFlight(Flight flight) {
        Flight flight1 = new Flight(flight.getCodeOfStartingAirport(), flight.getCodeOfDestinationAirport(), flight.getDepartureTime(),
                flight.getFlightDuration());

        return Optional.of(this.flightRepository.save(flight1));
    }

    @Override
    public Optional<Flight> addFlight(FlightDto flightDto) {


        Flight flight = new Flight(flightDto.getCodeOfStartingAirport(), flightDto.getCodeOfDepartureAirport(),
                flightDto.getDepartureTime(),
                flightDto.getFlightDuration());

        return Optional.of(this.flightRepository.save(flight));
    }

    @Override
    public Optional<Flight> editFlight(Flight flight) {


        Optional<Flight> flight1 = this.flightRepository.findById(flight.getId());
        flight1.get().setFlightDuration(flight.getFlightDuration());
        flight1.get().setDepartureTime(flight.getDepartureTime());
        flight1.get().setCodeOfDestinationAirport(flight.getCodeOfDestinationAirport());
        flight1.get().setCodeOfStartingAirport(flight.getCodeOfStartingAirport());
        return Optional.of(this.flightRepository.save(flight1.get()));
    }


    @Override
    public Optional<Flight> editFlight(Long id, FlightDto flightDto) throws InvalidFlightException {
        if (this.flightRepository.findById(id).isPresent()) {

            Optional<Flight> flight = this.flightRepository.findById(id);
            flight.get().setFlightDuration(flightDto.getFlightDuration());
            flight.get().setDepartureTime(flightDto.getDepartureTime());
            flight.get().setCodeOfDestinationAirport(flightDto.getCodeOfDepartureAirport());
            flight.get().setCodeOfStartingAirport(flightDto.getCodeOfStartingAirport());
            return Optional.of(this.flightRepository.save(flight.get()));
        } else throw new InvalidFlightException("Inalid");
    }


    //    Provide an implementation for showing all flights from an airport with a specific code →
//    The result should be returned in JSON format →
//    First the name of the airport,
//    then all flights ordered first lexicographically according to the code of the destination airport,
//    and then the flights to that airport according to the departure time.


    @Override
    public FlightListDto getFlightsByAirportCode(String code) {
        Optional<Airport> airport = this.airportRepository.findByAirportCode(code);
        String airportName = airport.get().getAirportName();

        //        if (airportName == null) {
//            throw new AirportNotFoundException("Airport with code " + airportCode + " not found.");
//        }
        List<Flight> flights = this.flightRepository.findByCodeOfStartingAirport(code);
        flights.sort(Comparator.comparing(Flight::getCodeOfDestinationAirport)
                .thenComparing(Flight::getDepartureTime));
//        flights.sort((f1, f2) -> {
//            int destinationAirportCode = f1.getCodeOfDestinationAirport().compareTo(f2.getCodeOfDestinationAirport());
//            if (destinationAirportCode != 0) {
//                return destinationAirportCode;
//
//            } else {
//                return f1.getDepartureTime().compareTo(f2.getDepartureTime());
//            }
//        });


        return new FlightListDto(airportName, flights);


    }


    @Override
    public List<Flight> findDirectFlightsFromGivenAirport(String code) {
        return this.flightRepository.findDirectFlightsForSpecificAirport(code);
    }


    @Override
    public List<Flight> findDirectFlights(String startingCode, String destinationCode) {
        return this.flightRepository.findDirectFLights(startingCode, destinationCode);
    }

}
