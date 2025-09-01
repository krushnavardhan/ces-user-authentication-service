package com.as.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.as.beans.AuthenticationRequest;
import com.as.beans.AuthenticationResponse;
import com.as.config.JwtService;
import com.as.entity.Registration;
import com.as.repository.RegistrationRepository;

@Service
public class AuthenticationService {

	@Autowired
	private RegistrationRepository repository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		Registration registration = repository.findByContactEmail(request.getEmail()).orElseThrow();
		String jwtToken = jwtService.generateToken(registration.getContactEmail());

		AuthenticationResponse response = new AuthenticationResponse();
		response.setAccessToken(jwtToken);
		return response;
	}

	public boolean isTokenValid(AuthenticationResponse response) {

		String accessToken = response.getAccessToken();
		String userEmail = jwtService.extractUsername(accessToken);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
		return jwtService.isTokenValid(accessToken, userDetails);
	}

}
