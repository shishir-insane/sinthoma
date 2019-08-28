package com.sk.sinthoma.core.auth.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sk.sinthoma.core.auth.rest.UserService;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    public CustomUserDetailsService(UserService users) {
	this.userService = users;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	return userService.findByUserName(userName)
		.orElseThrow(() -> new UsernameNotFoundException("Username: " + userName + " not found"));
    }
}