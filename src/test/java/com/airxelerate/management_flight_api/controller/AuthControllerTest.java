package com.airxelerate.management_flight_api.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.airxelerate.management_flight_api.controllers.AuthController;
import com.airxelerate.management_flight_api.dto.LoginRequest;
import com.airxelerate.management_flight_api.dto.LoginResponse;
import com.airxelerate.management_flight_api.enums.Role;
import com.airxelerate.management_flight_api.models.User;
import com.airxelerate.management_flight_api.security.CustomUserDetails;
import com.airxelerate.management_flight_api.security.JwtUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
	//This is used by Spring Security to verify credentials.
    @Mock
    private AuthenticationManager authenticationManager;
 
    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AuthController authController;

    private LoginRequest loginRequest;
    private CustomUserDetails userDetails;
    private User user;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("password");

        user = new User();
        user.setUsername("testUser");
        user.setRole(Role.USER);

        userDetails = new CustomUserDetails(user);
    }

    @Test
    void authenticateUser_ShouldReturnJwtResponse_WhenCredentialsAreValid() {
        // Arrange
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtUtils.generateJwtToken("testUser")).thenReturn("mocked-jwt");

        // Act
        ResponseEntity<?> response = authController.authenticateUser(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof LoginResponse);

        LoginResponse loginResponse = (LoginResponse) response.getBody();
        assertEquals("mocked-jwt", loginResponse.getToken());
        assertEquals("testUser", loginResponse.getUsername());
        assertEquals(Role.USER, loginResponse.getRole());

        verify(authenticationManager, times(1)).authenticate(any());
        verify(jwtUtils, times(1)).generateJwtToken("testUser");
    }

    @Test
    void authenticateUser_ShouldThrowAuthenticationException_WhenInvalidCredentials() {
        // Arrange
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new AuthenticationException("Invalid credentials") {});

        // Act & Assert
        AuthenticationException exception = assertThrows(AuthenticationException.class,
                () -> authController.authenticateUser(loginRequest));

        assertEquals("Invalid credentials", exception.getMessage());
        verify(authenticationManager, times(1)).authenticate(any());
        verify(jwtUtils, never()).generateJwtToken(anyString());
    }
    @Test
    void authenticateUser_ShouldThrowException_WhenUsernameOrPasswordIsEmpty() {
        // Arrange: username and password empty
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("");
        loginRequest.setPassword("");

        // Simulate Spring Security rejecting empty credentials
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new AuthenticationException("Username or password cannot be empty") {});

        // Act + Assert
        AuthenticationException exception = assertThrows(
                AuthenticationException.class,
                () -> authController.authenticateUser(loginRequest)
        );

        assertEquals("Username or password cannot be empty", exception.getMessage());

        // Verify that JWT was never generated
        verify(jwtUtils, never()).generateJwtToken(anyString());
    }
}
