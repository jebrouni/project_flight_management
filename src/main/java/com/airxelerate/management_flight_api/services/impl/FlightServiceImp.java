package com.airxelerate.management_flight_api.services.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.airxelerate.management_flight_api.models.Flight;
import com.airxelerate.management_flight_api.repositories.FlightRepository;
import com.airxelerate.management_flight_api.services.FlightService;



@Service
public class FlightServiceImp implements FlightService {

	private final FlightRepository flightRepository;

	public FlightServiceImp(FlightRepository flightRepository) {

		this.flightRepository = flightRepository;
	}

	@Override
	 
	public Flight addFlight(Flight flight) {
		
		return flightRepository.save(flight);
	}

	@Override
	 
	public List<Flight> getAllFlight() {
		
		return flightRepository.findAll();
	}

	@Override
	public Optional<Flight> getFlightById(Long Id) {
		// TODO Auto-generated method stub
		return flightRepository.findById(Id);
	}

	@Override
	public void deleteFlightById(Long Id) {
		flightRepository.deleteById(Id);

	}

}
