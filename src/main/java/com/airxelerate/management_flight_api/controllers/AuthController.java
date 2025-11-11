package com.airxelerate.management_flight_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airxelerate.management_flight_api.dto.LoginRequest;
import com.airxelerate.management_flight_api.dto.LoginResponse;
import com.airxelerate.management_flight_api.security.CustomUserDetails;
import com.airxelerate.management_flight_api.security.JwtUtils;

/**
 * REST Controller for Authentication.
 *
 * Handles login requests and returns a JWT token upon successful
 * authentication. This controller is accessible without authentication at
 * "/api/auth/**".
 * 
 * @author Soufian
 * @version 1.0
 * @since 2025-11-10
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private static final Logger logger = LogManager.getLogger(AuthController.class);

	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}

	/**
	 * Authenticates a user and returns a JWT token.
	 *
	 * @param loginRequest contains username and password
	 * @return a JwtResponse containing the JWT token and the username
	 */
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		logger.debug("Attempting authentication for user: {}", loginRequest.getUsername());

		try {
			// Authenticate user credentials
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			logger.info("User authenticated successfully: {}", userDetails.getUsername());

			// Generate JWT token
			String jwt = jwtUtils.generateJwtToken(userDetails.getUsername());
			logger.debug("JWT token generated for user: {}", userDetails.getUsername());

			// Create and return response
			LoginResponse response = new LoginResponse(jwt, userDetails.getUsername(), userDetails.getUser().getRole());

			logger.info("Authentication successful for user: {}. Role: {}", userDetails.getUsername(),
					userDetails.getUser().getRole());

			return ResponseEntity.ok(response);

		} catch (AuthenticationException e) {
			logger.error("Authentication failed for user: {}. Error: {}", loginRequest.getUsername(), e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("Unexpected error during authentication process", e);
			throw e;
		}
	}
}
