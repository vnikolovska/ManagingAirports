package com.codeit.airports.managingairports.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalTime;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    private String codeOfStartingAirport;
    private String codeOfDepartureAirport;
    private Integer departureTime;

    private Integer flightDuration;

    public Flight() {
    }

    public Flight(String codeOfStartingAirport, String codeOfDepartureAirport, Integer departureTime, Integer flightDuration) {
        this.codeOfStartingAirport = codeOfStartingAirport;
        this.codeOfDepartureAirport = codeOfDepartureAirport;
        this.departureTime = departureTime;
        this.flightDuration = flightDuration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeOfStartingAirport() {
        return codeOfStartingAirport;
    }

    public void setCodeOfStartingAirport(String codeOfStartingAirport) {
        this.codeOfStartingAirport = codeOfStartingAirport;
    }

    public String getCodeOfDepartureAirport() {
        return codeOfDepartureAirport;
    }

    public void setCodeOfDepartureAirport(String codeOfDepartureAirport) {
        this.codeOfDepartureAirport = codeOfDepartureAirport;
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
