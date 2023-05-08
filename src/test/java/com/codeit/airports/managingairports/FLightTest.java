package com.codeit.airports.managingairports;
import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;
import com.codeit.airports.managingairports.service.AirportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FLightTest {






        @Autowired
        private AirportService airportService;

        // add test methods here
        @Test
        public void testDeleteAirport() throws InvalidFlightException {
            // create a test airport
            Airport airport = new Airport("Test Airport", "TST", "Test City",5);

            // save the airport to the database
            airportService.addAirport(airport);

            // verify that the airport was saved successfully
            assertNotNull(airport.getAirportName());

            // delete the airport
            airportService.deleteAirport("Test Airport");

            // verify that the airport was deleted
           // assertNull(airportService.findByname("Test Airport"));
        }



}
