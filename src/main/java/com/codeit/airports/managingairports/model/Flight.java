package com.codeit.airports.managingairports.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String codeOfStartingAirport;

    private String codeOfDestinationAirport;
    private Integer departureTime;

    private Integer flightDuration;

    public Flight() {
    }

    public Flight(String codeOfStartingAirport, String codeOfDestinationAirport, Integer departureTime, Integer flightDuration) {
        this.codeOfStartingAirport = codeOfStartingAirport;
        this.codeOfDestinationAirport = codeOfDestinationAirport;
        this.departureTime = departureTime;
        this.flightDuration = flightDuration;
    }

    public String getCodeOfStartingAirport() {
        return codeOfStartingAirport;
    }

    public void setCodeOfStartingAirport(String codeOfStartingAirport) {
        this.codeOfStartingAirport = codeOfStartingAirport;
    }

    public String getCodeOfDestinationAirport() {
        return codeOfDestinationAirport;
    }

    public void setCodeOfDestinationAirport(String codeOfDestinationAirport) {
        this.codeOfDestinationAirport = codeOfDestinationAirport;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Integer departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(Integer flightDuration) {
        this.flightDuration = flightDuration;
    }
}
