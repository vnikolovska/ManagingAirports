package com.codeit.airports.managingairports.repository;

import com.codeit.airports.managingairports.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport,String> {
}
