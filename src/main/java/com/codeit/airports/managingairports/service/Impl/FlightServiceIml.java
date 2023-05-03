package com.codeit.airports.managingairports.service.Impl;

import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.Flight;
import com.codeit.airports.managingairports.repository.FlightRepository;
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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceIml {


    @Autowired
    private FlightRepository flightRepository;
    public List<Flight> importFlightsCsv() throws IOException, CsvException {
        Reader reader = new FileReader("src/main/resources/data/flights.csv");
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();


        List<String[]> rows = csvReader.readAll();
        List<Flight> flights = new ArrayList<>();

        for (String[] row : rows) {


            Flight flight = new Flight(row[0], row[1], Integer.parseInt(row[2]),Integer.parseInt(row[3]));

            flights.add(flight);
            flightRepository.save(flight);

        }
        return flights;
    }
}
