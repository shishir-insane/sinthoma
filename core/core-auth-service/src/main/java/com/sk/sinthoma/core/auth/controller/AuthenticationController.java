package com.sk.sinthoma.core.auth.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.sk.sinthoma.core.auth.jwt.JwtTokenProvider;
import com.sk.sinthoma.core.auth.rest.UserService;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService users;

    @PostMapping("/signin")
    public ResponseEntity<Map<Object, Object>> signin(@RequestBody AuthenticationRequest data) {

	try {
	    String username = data.getUsername();
	    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
	    String token = jwtTokenProvider.createToken(username, this.users.findByUserName(username)
		    .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
	    return ok(ImmutableMap.of("username", username, "token", token));
	} catch (AuthenticationException e) {
	    throw new BadCredentialsException("Invalid username/password supplied");
	}
    }
}