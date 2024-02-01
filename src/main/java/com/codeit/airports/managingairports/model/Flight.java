package com.codeit.airports.managingairports.model;

import javax.persistence.*;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    private Airport codeOfStartingAirport;

    @ManyToOne
    private Airport codeOfDestinationAirport;
    private Integer departureTime;

    private Integer flightDuration;

    public Flight() {
    }

    public Flight(Airport codeOfStartingAirport, Airport codeOfDestinationAirport, Integer departureTime, Integer flightDuration) {
        this.codeOfStartingAirport = codeOfStartingAirport;
        this.codeOfDestinationAirport = codeOfDestinationAirport;
        this.departureTime = departureTime;
        this.flightDuration = flightDuration;
    }

    public Airport getCodeOfStartingAirport() {
        return codeOfStartingAirport;
    }

    public void setCodeOfStartingAirport(Airport codeOfStartingAirport) {
        this.codeOfStartingAirport = codeOfStartingAirport;
    }

    public Airport getCodeOfDestinationAirport() {
        return codeOfDestinationAirport;
    }

    public void setCodeOfDestinationAirport(Airport codeOfDestinationAirport) {
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
