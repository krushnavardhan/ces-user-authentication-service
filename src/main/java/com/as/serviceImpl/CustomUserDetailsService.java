package com.as.serviceImpl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.as.entity.Registration;
import com.as.repository.RegistrationRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private RegistrationRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Registration registration = repo.findByContactEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		return new org.springframework.security.core.userdetails.User(registration.getContactEmail(),
				registration.getPassword(), Collections.emptyList());
	}

}
