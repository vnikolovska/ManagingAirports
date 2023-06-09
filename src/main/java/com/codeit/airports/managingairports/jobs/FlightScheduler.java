package com.codeit.airports.managingairports.jobs;

import com.codeit.airports.managingairports.model.Flight;
import com.codeit.airports.managingairports.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
public class FlightScheduler {


    @Autowired
    private FlightRepository flightRepository;

    @Scheduled(cron = "0 0 1 * * ?")
    public void deleteFlightsWithLongDuration() {
        List<Flight> flights = flightRepository.findAll();
        for (Flight flight : flights) {
            Duration duration = Duration.ofMinutes(flight.getFlightDuration());
            if (duration.toMinutes() > 600) {
                flightRepository.delete(flight);
            }
        }
    }

}


