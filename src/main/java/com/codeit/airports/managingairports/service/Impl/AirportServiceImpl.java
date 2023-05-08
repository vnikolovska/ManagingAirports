package com.codeit.airports.managingairports.service.Impl;


import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.dto.AirportDto;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;
import com.codeit.airports.managingairports.repository.AirportRepository;
import com.codeit.airports.managingairports.service.AirportService;
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
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {
    @Autowired
    private AirportRepository airportRepository;

    @Override
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


    @Override
    public Optional<Airport> addAirport(Airport airport) {
//        if (this.airportRepository.findByAirportCode(airport.getAirportCode())){
//
//        }
        Airport air = new Airport(airport.getAirportName(), airport.getCountryName(),
                airport.getAirportCode(), airport.getNumberOfAnnuallyPassengers());
        return Optional.of(this.airportRepository.save(air));
    }

    @Override
    public Optional<Airport> addAirport(AirportDto airportDto) throws InvalidFlightException {
        if (this.airportRepository.findByAirportCode(airportDto.getAirportCode()).isPresent()) {
            throw new InvalidFlightException("Inavlid airports");
        }
        Airport airport = new Airport(airportDto.getAirportName(), airportDto.getCountryName(),
                airportDto.getAirportCode(), airportDto.getNumberOfAnnuallyPassengers());
        return Optional.of(this.airportRepository.save(airport));
    }


    @Override
    public void deleteAirport(String code) throws InvalidFlightException {
        if (this.airportRepository.findByAirportCode(code).isPresent()) {
            Optional<Airport> airport = this.airportRepository.findByAirportCode(code);
            this.airportRepository.delete(airport.get());
        } else {
            throw new InvalidFlightException("Invaligh");
        }

    }

    //
//    Provide an implementation to
//        return the airport with most passengers during one year
//        for specific country (country name)
    @Override
    public Optional<Airport> findAirportWithMostPassengersByCountry(String country) {
        return
                this.airportRepository.findMaxNumberAnnuallyPassengersByCountry(country);

//        List<Airport> airports = this.airportRepository.findByCountryName(country);
//        return Optional.of(airports.stream()
//                .max(Comparator.comparing(Airport::getNumberOfAnnuallyPassengers)));

    }


}

