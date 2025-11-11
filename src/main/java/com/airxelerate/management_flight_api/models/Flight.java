package com.airxelerate.management_flight_api.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flights")
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Carrier code is required")
	@Pattern(regexp = "^[A-Za-z]{2}$")
	
	private String carrierCode;
	@Pattern(regexp = "^\\d{4}$", message = "Flight number must be 4 digits")
	private String flightNumber;

	@NotNull(message = "Flight date is required")
	private Date flightDate;

	@NotNull(message = "Origin is required")
	@Pattern(regexp = "^[A-Z]{3}$", message = "Origin must be 3 uppercase letters (IATA airport code)")
	private String origin;
	
	@NotNull(message = "Destination is required")
	@Pattern(regexp = "^[A-Z]{3}$", message = "Destination must be 3 uppercase letters (IATA airport code)")
	@Pattern(regexp = "^[A-Za-z]{3}$")
	private String destination;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

}
