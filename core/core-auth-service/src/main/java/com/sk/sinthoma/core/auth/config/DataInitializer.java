package com.sk.sinthoma.core.auth.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sk.sinthoma.core.auth.model.User;
import com.sk.sinthoma.core.auth.rest.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
	log.info("Initializing users auth data.");
	if (!userService.findByUserName("john.doe").isPresent()) {
	    userService.saveNewUser(User.builder().username("john.doe").firstName("John").lastName("Doe")
		    .emailId("john.doe@mail.com").isAccountNonExpired(true).isAccountNonLocked(true)
		    .isCredentialsNonExpired(true).isEnabled(true).imagePath("")
		    .password(this.passwordEncoder.encode("password")).roles(Arrays.asList("ROLE_USER")).build());
	}
	log.info("Users auth data initialized.");
    }
}