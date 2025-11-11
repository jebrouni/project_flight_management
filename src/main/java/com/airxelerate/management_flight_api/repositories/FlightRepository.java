package com.airxelerate.management_flight_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airxelerate.management_flight_api.models.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{

}
