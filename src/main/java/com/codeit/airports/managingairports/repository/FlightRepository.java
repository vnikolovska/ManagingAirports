package com.codeit.airports.managingairports.repository;

import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByCodeOfStartingAirport(Airport codeOfStartingAirport);

    List<Flight> findByCodeOfDestinationAirport(Airport codeOfDestinationAirport);


    @Query("select f from Flight f where f.codeOfDestinationAirport.airportCode = :code")
    List<Flight> findDirectFlightsForSpecificAirport(String code);


    @Query("select f from Flight f  where f.codeOfStartingAirport.airportCode= :startingCode AND  f.codeOfDestinationAirport.airportCode= :destinationCode")
    List<Flight> findDirectFLights(String startingCode, String destinationCode);
}
