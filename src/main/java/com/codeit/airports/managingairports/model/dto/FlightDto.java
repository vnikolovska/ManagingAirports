package com.codeit.airports.managingairports.model.dto;


import lombok.Data;

@Data
public class FlightDto {


    private String codeOfStartingAirport;
    private String codeOfDestinationAirport;
    private Integer departureTime;

    private Integer flightDuration;

    public FlightDto(String codeOfStartingAirport, String codeOfDestinationAirport, Integer departureTime, Integer flightDuration) {

        this.codeOfStartingAirport = codeOfStartingAirport;
        this.codeOfDestinationAirport = codeOfDestinationAirport;
        this.departureTime = departureTime;
        this.flightDuration = flightDuration;
    }
}
