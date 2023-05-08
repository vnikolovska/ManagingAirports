package com.codeit.airports.managingairports.model.dto;

import com.codeit.airports.managingairports.model.Flight;
import lombok.Data;

import java.util.List;

@Data
public class FlightListDto {

    private String airportName;
    private List<Flight> flights;

    public FlightListDto(String airportName, List<Flight> flights) {
        this.airportName = airportName;
        this.flights = flights;
    }
}
