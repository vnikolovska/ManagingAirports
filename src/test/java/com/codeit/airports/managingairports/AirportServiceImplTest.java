package com.codeit.airports.managingairports;


import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.Flight;
import com.codeit.airports.managingairports.model.dto.AirportDto;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;
import com.codeit.airports.managingairports.repository.AirportRepository;
import com.codeit.airports.managingairports.repository.FlightRepository;
import com.codeit.airports.managingairports.service.Impl.AirportServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AirportServiceImplTest {


    @Mock
    AirportRepository airportRepository;

    @InjectMocks
    AirportServiceImpl airportService;

    @Mock
    FlightRepository flightRepository;


    //
    @Test
    public void testAddAirport() throws InvalidFlightException {
        // Prepare test data
        Airport airport = new Airport("MK Airport", "Macedonia", "MK", 1000);

        // Mock the airport repository
        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        // Call the method to be tested
        Optional<Airport> addedAirport = airportService.addAirport(airport);

        // Verify the result
        assertTrue(addedAirport.isPresent());
        assertEquals(airport, addedAirport.get());
        verify(airportRepository, times(1)).save(any(Airport.class));
    }

    @Test
    public void testAddAirportDto() throws InvalidFlightException {
        // create test data
        AirportDto airportDto = new AirportDto("MK", "Macedonia International Airport", "Macedonia", 1200);
        Airport airport = new Airport(airportDto.getAirportName(), airportDto.getCountryName(),
                airportDto.getAirportCode(), airportDto.getNumberOfAnnuallyPassengers());


        when(airportRepository.findByAirportCode(airportDto.getAirportCode())).thenReturn(Optional.empty());
        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        // call the method to be tested
        Optional<Airport> result = airportService.addAirport(airportDto);

        // verify that the result is as expected
        assertTrue(result.isPresent());
        assertEquals(airport.getAirportName(), result.get().getAirportName());
        assertEquals(airport.getCountryName(), result.get().getCountryName());
        assertEquals(airport.getAirportCode(), result.get().getAirportCode());
        assertEquals(airport.getNumberOfAnnuallyPassengers(), result.get().getNumberOfAnnuallyPassengers());


        verify(airportRepository, times(1)).findByAirportCode(airportDto.getAirportCode());
        verify(airportRepository, times(1)).save(any(Airport.class));
    }


    @Test
    public void deleteAirportTest() throws InvalidFlightException {
        // Create a sample airport
        Airport airport = new Airport("ABC", "Airport Name", "City", 115);

        // Create a sample list of flights
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(airport, airport, 115, 120));
        flights.add(new Flight(airport, airport, 115, 180));

        when(airportRepository.findByAirportCode("ABC")).thenReturn(Optional.of(airport));

        when(flightRepository.findByCodeOfStartingAirport(airport)).thenReturn(flights);
        when(flightRepository.findByCodeOfDestinationAirport(airport)).thenReturn(flights);

        // Call the method to be tested
        airportService.deleteAirport("ABC");

        verify(airportRepository, times(1)).delete(airport);

        verify(flightRepository, times(2)).deleteAll(flights);
    }

    @Test
    public void testDeleteAirportAirportNotFound() {
        // Prepare test data
        String airportCode = "ABC";

        // Configure mock repository
        when(airportRepository.findByAirportCode(airportCode)).thenReturn(Optional.empty());

        // Call the method to be tested and assert that it throws the expected exception
        assertThrows(InvalidFlightException.class, () -> airportService.deleteAirport(airportCode));

        // Verify that the repository delete method is not called
        verify(airportRepository, never()).delete(any(Airport.class));
    }


    @Test
    public void testFindAirportWithMostPassengersByCountry() {

        String country = "USA";
        Airport airport1 = new Airport("Airport 1", country, "A1", 1000);
        Airport airport2 = new Airport("Airport 2", country, "A2", 2000);
        Airport airport3 = new Airport("Airport 3", country, "A3", 3000);

        when(airportRepository.findMaxNumberAnnuallyPassengersByCountry(country)).thenReturn(Optional.of(airport3));


        Optional<Airport> result = airportService.findAirportWithMostPassengersByCountry(country);

        // Assert
        assertEquals(airport3, result.get());
    }


}
