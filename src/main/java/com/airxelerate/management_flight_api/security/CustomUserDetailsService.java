package com.airxelerate.management_flight_api.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.airxelerate.management_flight_api.exceptions.UserNotFoundException;
import com.airxelerate.management_flight_api.models.User;
import com.airxelerate.management_flight_api.repositories.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
        return new CustomUserDetails(user);
    }
}

