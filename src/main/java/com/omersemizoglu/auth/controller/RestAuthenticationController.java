package com.omersemizoglu.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.omersemizoglu.controller.RestBaseController;
import com.omersemizoglu.controller.RootEntity;
import com.omersemizoglu.dto.auth.AuthRequest;
import com.omersemizoglu.dto.auth.AuthResponse;
import com.omersemizoglu.dto.response.DtoUser;
import com.omersemizoglu.dto.auth.RefreshTokenRequest;
import com.omersemizoglu.auth.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationController extends RestBaseController{

	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/register")
	public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest input) {
		return ok(authenticationService.register(input));
	}

	@PostMapping("/authenticate")
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
		return ok(authenticationService.authenticate(input));
	}

	@PostMapping("/refreshToken")
	public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest input) {
		return ok(authenticationService.refreshToken(input));
	}
}

