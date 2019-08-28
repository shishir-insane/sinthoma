package com.sk.sinthoma.core.auth.controller;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.sk.sinthoma.core.auth.jwt.JwtTokenProvider;
import com.sk.sinthoma.core.auth.model.AuthenticationRequest;
import com.sk.sinthoma.core.auth.service.UserService;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService users;

    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> signin(@RequestBody AuthenticationRequest data) {

	try {
	    String username = data.getUsername();
	    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
	    String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username)
		    .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
	    return ok(ImmutableMap.of("username", username, "token", token));
	} catch (AuthenticationException e) {
	    throw new BadCredentialsException("Invalid username/password supplied");
	}
    }
    
    @GetMapping("/me")
    public ResponseEntity<Map<Object, Object>> currentUser(@AuthenticationPrincipal UserDetails userDetails) {
	final Map<Object, Object> model = new HashMap<>();
	model.put("username", userDetails.getUsername());
	model.put("roles", userDetails.getAuthorities().stream().map(a -> ((GrantedAuthority) a).getAuthority())
		.collect(toList()));
	return ok(model);
    }
}