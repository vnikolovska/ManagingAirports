package com.codeit.airports.managingairports.model.dto;

import lombok.Data;

@Data
public class AirportDto {
    String airportCode;
    String airportName;
    String countryName;
    Integer numberOfAnnuallyPassengers;

    public AirportDto(String airportCode, String airportName, String countryName, Integer numberOfAnnuallyPassengers) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.countryName = countryName;
        this.numberOfAnnuallyPassengers = numberOfAnnuallyPassengers;
    }
}
