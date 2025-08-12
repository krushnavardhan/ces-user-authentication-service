package com.as.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	Optional<Registration> findByContactEmail(String email);

}
