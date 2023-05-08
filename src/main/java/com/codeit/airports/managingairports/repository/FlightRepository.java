package com.codeit.airports.managingairports.repository;

import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByCodeOfStartingAirport(String code);

    @Query("SELECT a FROM  Airport  a where  a.countryName= :country AND  a.numberOfAnnuallyPassengers =" +
            "(select max (a2.numberOfAnnuallyPassengers) FROM Airport  a2 where a2.countryName = :country)")
    Optional<Airport> findMaxNumberAnnuallyPassengersByCountry(String country);

    //Provide an implementation to show all direct flights to specific airport A (code of airport is unique)


    // @Query("SELECT f FROM Flight WHERE f.codeOfDepartureAirport= :code")
    @Query("select f from Flight f where f.codeOfDestinationAirport = :code")
    List<Flight> findDirectFlightsForSpecificAirport(String code);


    @Query("select f from Flight f  where f.codeOfStartingAirport= :startingCode AND  f.codeOfDestinationAirport= :destinationCode")
    List<Flight> findDirectFLights(String startingCode, String destinationCode);
}
