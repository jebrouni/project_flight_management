package com.airxelerate.management_flight_api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.airxelerate.management_flight_api.controllers.FlightController;
import com.airxelerate.management_flight_api.models.Flight;
import com.airxelerate.management_flight_api.services.FlightService;

@ExtendWith(MockitoExtension.class)
public class FlightControllerTest {
	@Mock
	private FlightService flightService;

	@InjectMocks
	private FlightController flightController;

	@Test
	void creatFlight_ValidFlight_ReturnsCreatedFlight() {
		// Arrange
		Flight creatFlight = new Flight();
		creatFlight.setFlightNumber("SO200");
		Flight savedFlight = new Flight();
		savedFlight.setId(1L);
		savedFlight.setFlightNumber("SO200");
		when(flightService.addFlight(any(Flight.class))).thenReturn(savedFlight);

		// Act
		ResponseEntity<Flight> response = flightController.creatFlight(creatFlight);

		// Assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(savedFlight);
	     verify(flightService, times(1)).addFlight(creatFlight);
	}
	
    @Test
    void getAllFlight_ReturnsListOfFlights() {
        // Arrange
        Flight f1 = new Flight();
        f1.setId(1L);
        Flight f2 = new Flight();
        f2.setId(1L);
        List<Flight> flights = Arrays.asList(f1, f2);

        when(flightService.getAllFlight()).thenReturn(flights);

        // Act
        List<Flight> result = flightController.getAllFlight();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(f1, f2);
        verify(flightService, times(1)).getAllFlight();
    }
    @Test
    void getFlightById_Found_ReturnsFlight() {
        // Arrange
        Flight flight = new Flight();
        flight.setId(1L);
        when(flightService.getFlightById(1L)).thenReturn(Optional.of(flight));

        // Act
        ResponseEntity<Flight> response = flightController.getFlightById(1L);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(flight);
        verify(flightService, times(1)).getFlightById(1L);
    }
	
    @Test
    void getFlightById_NotFound_Returns404() {
        // Arrange
        when(flightService.getFlightById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Flight> response = flightController.getFlightById(1L);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(flightService, times(1)).getFlightById(1L);
    }

    @Test
    void deleteFlightById_Success_ReturnsOk() {
        // Arrange
        doNothing().when(flightService).deleteFlightById(1L);

        // Act
        ResponseEntity<Void> response = flightController.deleteFlightById(1L);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(flightService, times(1)).deleteFlightById(1L);
    }
    @Test
    void deleteFlightById_NotFound_Returns404() {
        // Arrange
        doThrow(new RuntimeException("Flight not found")).when(flightService).deleteFlightById(1L);

        // Act
        ResponseEntity<Void> response = flightController.deleteFlightById(1L);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(flightService, times(1)).deleteFlightById(1L);
    }
    
}



