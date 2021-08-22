package com.javafsd.brijesh.springsecuriy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javafsd.brijesh.springsecuriy.entity.JwtRequest;
import com.javafsd.brijesh.springsecuriy.entity.JwtResponse;
import com.javafsd.brijesh.springsecuriy.service.UserService;
import com.javafsd.brijesh.springsecuriy.utility.JwtUtility;

@RestController
public class HelloController {
	
	@Autowired
	private JwtUtility jwtUtility;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String home() {
		return "Welcome Spring Security";
	}

	@PostMapping("/authenticate")
	public JwtResponse autenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							jwtRequest.getUsername(),
							jwtRequest.getPassword()
							)
					);
		}catch(BadCredentialsException bce) {
			throw new Exception("INVALID_CREDENTIAL", bce);
		}
		
		final UserDetails userDetails =
				userService.loadUserByUsername(jwtRequest.getUsername());
		
		final String token = 
				jwtUtility.generateToken(userDetails);
				
		return new JwtResponse(token);
		
	}
}
