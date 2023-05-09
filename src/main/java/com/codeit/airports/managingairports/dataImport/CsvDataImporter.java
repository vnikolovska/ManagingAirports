package com.codeit.airports.managingairports.dataImport;


import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.Flight;
import com.codeit.airports.managingairports.repository.AirportRepository;
import com.codeit.airports.managingairports.repository.FlightRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CsvDataImporter {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightRepository flightRepository;


    @PostConstruct
    public List<Airport> importAirportCsv() throws IOException, CsvException {

        Reader reader = new FileReader("src/main/resources/data/airports.csv");
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();


        List<String[]> rows = csvReader.readAll();
        List<Airport> airports = new ArrayList<>();

        for (String[] row : rows) {


            Airport airport = new Airport(row[0], row[1], row[2], Integer.parseInt(row[3]));

            airports.add(airport);
            airportRepository.save(airport);


        }
        return airports;
    }


    @PostConstruct
    public List<Flight> importFlightsCsv() throws IOException, CsvException {
        Reader reader = new FileReader("src/main/resources/data/flights.csv");
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();


        List<String[]> rows = csvReader.readAll();
        List<Flight> flights = new ArrayList<>();

        for (String[] row : rows) {
            Optional<Airport> startingAirport = airportRepository.findByAirportCode(row[0]);
            Optional<Airport> destinationAirport = airportRepository.findByAirportCode(row[1]);

            Flight flight = new Flight(startingAirport.get(), destinationAirport.get(), Integer.parseInt(row[2]), Integer.parseInt(row[3]));

            flights.add(flight);
            flightRepository.save(flight);

        }
        return flights;
    }
}
