package com.codeit.airports.managingairports.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Airport {


    @Id
    private String airportCode;
    private String airportName;
    private String countryName;


    private Integer numberOfAnnuallyPassengers;

    public Airport() {
    }

    public Airport(String airportName, String countryName, String airportCode, Integer numberOfAnnuallyPassengers) {
        this.airportName = airportName;
        this.countryName = countryName;
        this.airportCode = airportCode;
        this.numberOfAnnuallyPassengers = numberOfAnnuallyPassengers;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public Integer getNumberOfAnnuallyPassengers() {
        return numberOfAnnuallyPassengers;
    }

    public void setNumberOfAnnuallyPassengers(Integer numberOfAnnuallyPassengers) {
        this.numberOfAnnuallyPassengers = numberOfAnnuallyPassengers;
    }
}
