package com.as.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.as.beans.AuthenticationRequest;
import com.as.beans.AuthenticationResponse;
import com.as.beans.RegistrationBean;
import com.as.service.RegistrationSVC;
import com.as.serviceImpl.AuthenticationService;

@RestController
@RequestMapping("/auth-service/auth")
public class RegistrationController {

	@Autowired
	private RegistrationSVC registrationSVC;
	
	@Autowired
	private AuthenticationService service;

	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody RegistrationBean registrationBean) {
		registrationSVC.saveOrUpdateUser(registrationBean);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}

}
