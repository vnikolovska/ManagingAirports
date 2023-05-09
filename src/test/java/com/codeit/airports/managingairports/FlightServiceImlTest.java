package com.codeit.airports.managingairports;

import com.codeit.airports.managingairports.model.Airport;
import com.codeit.airports.managingairports.model.Flight;
import com.codeit.airports.managingairports.model.dto.FlightDto;
import com.codeit.airports.managingairports.model.exceptions.InvalidFlightException;
import com.codeit.airports.managingairports.repository.AirportRepository;
import com.codeit.airports.managingairports.repository.FlightRepository;
import com.codeit.airports.managingairports.service.Impl.FlightServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceImlTest {

    @Mock
    FlightRepository flightRepository;
    @InjectMocks
    FlightServiceImpl flightService;

    @Mock
    AirportRepository airportRepository;


    @Test
    public void testAddFlight() throws InvalidFlightException {


        Airport startingAirport = new Airport("SFO", "San Francisco International", "United States", 552);
        Airport destinationAirport = new Airport("PHX", "Phoenix Sky Harbor International", "United States", 188);

        Flight flight = new Flight(startingAirport, destinationAirport, 552, 188);
        when(airportRepository.findByAirportCode("SFO")).thenReturn(Optional.of(startingAirport));
        when(airportRepository.findByAirportCode("PHX")).thenReturn(Optional.of(destinationAirport));
        when(flightRepository.save(Mockito.any(Flight.class))).thenReturn(new Flight(startingAirport, destinationAirport, 552, 188));

        // when
        Optional<Flight> result = flightService.addFlight(flight);

        // then
        assertEquals("SFO", result.get().getCodeOfStartingAirport().getAirportName());
        assertEquals("PHX", result.get().getCodeOfDestinationAirport().getAirportName());
        assertEquals(552, result.get().getDepartureTime().intValue());
        assertEquals(188, result.get().getFlightDuration().intValue());
    }


    @Test
    public void testAddFlightDto() throws InvalidFlightException {
        // given
        FlightDto flightDto = new FlightDto("SFO", "PHX", 552, 188);


        Airport startingAirport = new Airport("SFO", "San Francisco International", "United States", 44399885);
        Airport destinationAirport = new Airport("PHX", "Phoenix Sky Harbor International", "United States", 40421611);

        when(airportRepository.findByAirportCode("SFO")).thenReturn(Optional.of(startingAirport));
        when(airportRepository.findByAirportCode("PHX")).thenReturn(Optional.of(destinationAirport));
        when(flightRepository.save(Mockito.any(Flight.class))).thenReturn(new Flight(startingAirport, destinationAirport, 552, 188));

        // when
        Optional<Flight> result = flightService.addFlight(flightDto);

        // then
        assertEquals("SFO", result.get().getCodeOfStartingAirport().getAirportName());
        assertEquals("PHX", result.get().getCodeOfDestinationAirport().getAirportName());
        assertEquals(552, result.get().getDepartureTime().intValue());
        assertEquals(188, result.get().getFlightDuration().intValue());
    }

    @Test(expected = InvalidFlightException.class)
    public void testAddFlightWithInvalidArguments() throws InvalidFlightException {
        // given
        FlightDto flightDto = new FlightDto("SFO", "PHX", -5, 188);


        Airport startingAirport = new Airport("SFO", "San Francisco International", "United States", 44399885);
        Airport destinationAirport = new Airport("PHX", "Phoenix Sky Harbor International", "United States", 40421611);

        when(airportRepository.findByAirportCode(anyString())).thenReturn(Optional.of(mock(Airport.class)));

        // when
        flightService.addFlight(flightDto);

        // then (expecting InvalidFlightException to be thrown)
    }


    @Test
    public void testFindDirectFlights() {
        Airport startingAirport = new Airport("ABC", "Airport A", "Country A", 1000);
        Airport destinationAirport = new Airport("XYZ", "Airport X", "Country X", 2000);
        Airport destinationAirport1 = new Airport("XY", "Airport X", "Country X", 2000);


        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(startingAirport, destinationAirport, 100, 60));
        flights.add(new Flight(destinationAirport, startingAirport, 200, 120));
        flights.add(new Flight(destinationAirport1, startingAirport, 300, 180));

        when(flightRepository.findDirectFLights("ABC", "XYZ")).thenReturn(flights);

        List<Flight> directFlights = flightService.findDirectFlights("ABC", "XYZ");

        verify(flightRepository).findDirectFLights("ABC", "XYZ");
        assertEquals(flights, directFlights);
    }


}
