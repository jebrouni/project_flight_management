package com.airxelerate.management_flight_api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.airxelerate.management_flight_api.models.Flight;
import com.airxelerate.management_flight_api.services.FlightService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flight")
@Tag(name = "Flight Management api", description = "Operations related to flight management")
public class FlightController {
	private final FlightService flightService;
	private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

	public FlightController(FlightService flightService) {
		this.flightService = flightService;

	}

	@PostMapping
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	@Operation(summary = "Create a new flight", description = "creat a new flight By ADMIN ", security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Flight created", content = @Content(schema = @Schema(implementation = Flight.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden") })
	public ResponseEntity<Flight> creatFlight(@RequestBody @Valid Flight flight) {
		logger.info("Attempting to add new flight: {}", flight.getFlightNumber());
		Flight saveFlighth = flightService.addFlight(flight);
		logger.info("Flight {} created successfully with ID: {}", saveFlighth.getFlightNumber(), saveFlighth.getId());
		return ResponseEntity.ok(saveFlighth);
	}

	@GetMapping("/All")
	@Operation(summary = "Get all flights", description = "Retrieve a list of all available flights")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved list flights", content = @Content(schema = @Schema(implementation = Flight.class))), })
	public List<Flight> getAllFlight() {

		List<Flight> listFlights = flightService.getAllFlight();
		logger.info("Returning {} flights", listFlights.size());

		return listFlights;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get flight by ID", description = "Retrieve a specific flight by its identifier")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Flight found", content = @Content(schema = @Schema(implementation = Flight.class))),
			@ApiResponse(responseCode = "404", description = "Flight not found") })
	public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
		Optional<Flight> flight = flightService.getFlightById(id);
		flight.ifPresentOrElse(
                f -> logger.info("Found flight {} with ID: {}", f.getFlightNumber(), id),
                () -> logger.warn("Flight with ID {} not found", id)
        );
		return flight.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @Operation(
            summary = "Delete a flight",
            description = "Remove a flight from the system by ID (Admin only)",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Flight not found")
    })
	public ResponseEntity<Void> deleteFlightById(@PathVariable Long id) {
		try {
	        logger.info("Attempting to delete flight with ID: {}", id);

			flightService.deleteFlightById(id);
	        logger.info(" successfully deleted", id);

			return ResponseEntity.ok().build();

		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}

	}
}
