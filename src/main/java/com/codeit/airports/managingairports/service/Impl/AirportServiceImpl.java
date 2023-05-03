package com.codeit.airports.managingairports.service.Impl;


import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.repository.AirportRepository;
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
import java.util.List;

@Service
public class AirportServiceImpl {
    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> importCsv() throws IOException, CsvException {
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


}

