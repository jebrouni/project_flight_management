package com.airxelerate.management_flight_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airxelerate.management_flight_api.models.User;

public interface UserRepository extends  JpaRepository<User, Long>{
	 Optional<User> findByUsername(String username);
}
