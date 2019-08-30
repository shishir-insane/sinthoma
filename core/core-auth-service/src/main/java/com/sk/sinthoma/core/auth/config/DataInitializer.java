/**
 * DataInitializer.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sk.sinthoma.core.auth.model.User;
import com.sk.sinthoma.core.auth.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
     */
    @Override
    public void run(String... args) throws Exception {
	log.info("Initializing userService auth data.");
	if (!userService.findByUsername("john.doe").isPresent()) {
	    userService.saveNewUser(User.builder().username("john.doe").emailId("john.doe@mail.com")
		    .isAccountNonExpired(true).isAccountNonLocked(true).isCredentialsNonExpired(true).isEnabled(true)
		    .password(passwordEncoder.encode("password")).roles(Arrays.asList("ROLE_USER")).build());
	}
	log.info("Users auth data initialized.");
    }
}