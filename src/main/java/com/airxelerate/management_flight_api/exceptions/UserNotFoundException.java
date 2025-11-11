package com.airxelerate.management_flight_api.exceptions;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String message) {
		super(message);
	}

}
