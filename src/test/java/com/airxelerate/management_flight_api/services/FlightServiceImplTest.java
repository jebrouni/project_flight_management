package com.airxelerate.management_flight_api.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;

import com.airxelerate.management_flight_api.models.Flight;
import com.airxelerate.management_flight_api.repositories.FlightRepository;
import com.airxelerate.management_flight_api.services.impl.FlightServiceImp;

@ExtendWith(MockitoExtension.class)
public class FlightServiceImplTest {
	@Mock
	private FlightRepository flightRepository;

	@InjectMocks
	private FlightServiceImp flightService;

	@Test
	void creatFlight_ValidateFlighth_ReturnsCreatFlight() {

		// Arrange
		Flight creatFlight = new Flight();
		creatFlight.setFlightNumber("L234");
		Flight saveFlight = new Flight();
		saveFlight.setId(1L);
		saveFlight.setFlightNumber("L234");
		when(flightRepository.save(any(Flight.class))).thenReturn(saveFlight);

		// Act
		Flight result = flightService.addFlight(creatFlight);

		// Assert
		assertThat(result).isEqualTo(saveFlight);
		verify(flightRepository).save(creatFlight);

	}

	@Test
	void getAllflights_ShouldReturnListFlights() {
		// Arrange
		Flight flight1 = new Flight();
		flight1.setId(1L);
		Flight flight2 = new Flight();
		flight2.setId(2L);
		List<Flight> flights = Arrays.asList(flight1, flight2);

		when(flightRepository.findAll()).thenReturn(flights);

		// Act
		List<Flight> result = flightService.getAllFlight();

		// Assert
		assertThat(result).hasSize(2);
		assertThat(result).containsExactly(flight1, flight2);
		verify(flightRepository).findAll();

	}

	@Test
	void getFlightById_whenExists_ShouldReturnFlight() {

		// Arrange
		Long flightId = 1L;
		Flight flight = new Flight();
		flight.setId(flightId);
		when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));

		// Act
		Optional<Flight> result = flightService.getFlightById(flightId);

		// Assert
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(flight);
		verify(flightRepository).findById(flightId);

	}

	@Test
	void getFlightById_whenNotExists_ShouldReturnFlight() {
		// Arrange
		Long flightId = 1L;
		Flight flight = new Flight();
		when(flightRepository.findById(flightId)).thenReturn(Optional.empty());
		// Act
		Optional<Flight> result = flightService.getFlightById(flightId);

		// Assert
		assertThat(result).isEmpty();
		verify(flightRepository).findById(flightId);

	}
	
	@Test
	void deletFlight_ShouldCallRpositoryDelete() {
		//Assert
		Long flightId = 1L;
		doNothing().when(flightRepository).deleteById(flightId);
		
		//Act
		flightService.deleteFlightById(flightId);
		//Assert 
		verify(flightRepository).deleteById(flightId);
	}
}
