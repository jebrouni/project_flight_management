package com.airxelerate.management_flight_api.services;

import java.util.List;
import java.util.Optional;

import com.airxelerate.management_flight_api.models.Flight;

public interface FlightService {

	public Flight addFlight(Flight flight);

	public List<Flight> getAllFlight();

	public Optional<Flight> getFlightById(Long Id);

	public void deleteFlightById(Long Id);

}
