/**
 * DefaultUserDetailsServiceTest.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sk.sinthoma.core.auth.model.User;
import com.sk.sinthoma.core.auth.repository.UserAuthRepository;

@SpringBootTest
public class DefaultUserDetailsServiceTest {

    private static final String JOHN_DOE = "john.doe";
    private static final String INVALID_USER = "invalid.user";
    private static final String PASSWORD = "password";

    @Autowired
    private DefaultUserDetailsService userService;

    @MockBean
    private UserAuthRepository userAuthRepository;

    private User user = null;

    /**
     * Inits the.
     */
    @BeforeEach
    public void init() {
	user = User.builder().username(JOHN_DOE).password(PASSWORD).build();
	when(userAuthRepository.findByUsername(JOHN_DOE)).thenReturn(Optional.of(user));
	when(userAuthRepository.save(user)).thenReturn(user);
    }

    /**
     * Test load user by username with valid username.
     */
    @Test
    public void testLoadUserByUsernameWithValidUsername() {
	final User user = userService.loadUserByUsername(JOHN_DOE);
	assertNotNull(user);
	assertEquals(JOHN_DOE, user.getUsername());
    }

    /**
     * Test load user by username with empty username.
     */
    @Test
    public void testLoadUserByUsernameWithEmptyUsername() {
	assertThrows(IllegalArgumentException.class, () -> {
	    userService.loadUserByUsername(StringUtils.EMPTY);
	});
    }

    /**
     * Test load user by username with invalid username.
     */
    @Test
    public void testLoadUserByUsernameWithInvalidUsername() {
	assertThrows(UsernameNotFoundException.class, () -> {
	    userService.loadUserByUsername(INVALID_USER);
	});
    }

    /**
     * Test find by username with valid username.
     */
    @Test
    public void testFindByUsernameWithValidUsername() {
	final Optional<User> user = userService.findByUsername(JOHN_DOE);
	assertThat(user).isNotEmpty();
	assertThat(JOHN_DOE).isEqualTo(user.get().getUsername());
    }

    /**
     * Test find by username with empty username.
     */
    @Test
    public void testFindByUsernameWithEmptyUsername() {
	assertThrows(IllegalArgumentException.class, () -> {
	    userService.findByUsername(StringUtils.EMPTY);
	});
    }

    /**
     * Test find by username with invalid username.
     */
    @Test
    public void testFindByUsernameWithInvalidUsername() {
	final Optional<User> user = userService.findByUsername(INVALID_USER);
	assertThat(user).isEmpty();
    }

    /**
     * Test save new user with valid user.
     */
    @Test
    public void testSaveNewUserWithValidUser() {
	final User responseUser = userService.saveNewUser(user);
	assertNotNull(responseUser);
	assertThat(JOHN_DOE).isEqualTo(responseUser.getUsername());
    }

    /**
     * Test save new user with empty user name.
     */
    @Test
    public void testSaveNewUserWithEmptyUserName() {
	assertThrows(IllegalArgumentException.class, () -> {
	    userService.saveNewUser(User.builder().username(StringUtils.EMPTY).password(PASSWORD).build());
	});
    }

    /**
     * Test save new user with empty password.
     */
    @Test
    public void testSaveNewUserWithEmptyPassword() {
	assertThrows(IllegalArgumentException.class, () -> {
	    userService.saveNewUser(User.builder().username(JOHN_DOE).password(StringUtils.EMPTY).build());
	});
    }
}