package com.codeit.airports.managingairports.model.dto;


import lombok.Data;

@Data
public class FlightDto {

    private Long id;

    private String codeOfStartingAirport;
    private String codeOfDepartureAirport;
    private Integer departureTime;

    private Integer flightDuration;


}
