package com.codeit.airports.managingairports.repository;

import com.codeit.airports.managingairports.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {

    Airport findByAirportName(String name);

    Optional<Airport> findByAirportCode(String airportCode);

    List<Airport> findByCountryName(String countryName);


    @Query("SELECT a FROM  Airport  a where  a.countryName= :country AND  a.numberOfAnnuallyPassengers =" +
            "(select max (a2.numberOfAnnuallyPassengers) FROM Airport  a2 where a2.countryName = :country)")
    Optional<Airport> findMaxNumberAnnuallyPassengersByCountry(String country);
//    @Query("SELECT MAX(a.numberOfAnnuallyPassengers) FROM Airport a WHERE a.countryName = :country")
//    Optional<Integer> findMaxNumberOfAnnuallyPassengersByCountry(String country);
}
