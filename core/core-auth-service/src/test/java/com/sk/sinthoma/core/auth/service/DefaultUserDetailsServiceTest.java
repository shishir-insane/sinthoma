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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sk.sinthoma.core.auth.model.User;
import com.sk.sinthoma.core.auth.repository.UserAuthRepository;

@SpringBootTest
public class DefaultUserDetailsServiceTest {

    private static final String JOHN_DOE = "john.doe";

    @Autowired
    private DefaultUserDetailsService userService;

    @Mock
    private UserAuthRepository userAuthRepository;

    @BeforeEach
    public void init() {
	User user = User.builder().username(JOHN_DOE).build();
	when(userAuthRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
	when(userAuthRepository.save(user)).thenReturn(user);
    }

    @Test
    public void testLoadUserByUsernameWithValidUsername() {
	User user = userService.loadUserByUsername(JOHN_DOE);
	assertNotNull(user);
	assertEquals(JOHN_DOE, user.getUsername());
    }

    @Test
    public void testLoadUserByUsernameWithEmptyUsername() {
	assertThrows(IllegalArgumentException.class, () -> {
	    userService.loadUserByUsername(StringUtils.EMPTY);
	});
    }

    @Test
    public void testLoadUserByUsernameWithInvalidUsername() {
	assertThrows(UsernameNotFoundException.class, () -> {
	    userService.loadUserByUsername("invaliduser");
	});
    }

    @Test
    public void testFindByUsernameWithValidUsername() {
	Optional<User> user = userService.findByUsername(JOHN_DOE);
	assertThat(user).isNotEmpty();
	assertThat(JOHN_DOE).isEqualTo(user.get().getUsername());
    }

    @Test
    public void testFindByUsernameWithEmptyUsername() {
	assertThrows(IllegalArgumentException.class, () -> {
	    userService.findByUsername(StringUtils.EMPTY);
	});
    }

    @Test
    public void testFindByUsernameWithInvalidUsername() {
	Optional<User> user = userService.findByUsername("invaliduser");
	assertThat(user).isEmpty();
    }

    @Test
    public void testSaveNewUserWithValidUser() {
	User user = userService.saveNewUser(User.builder().username(JOHN_DOE).password("password").build());
	assertNotNull(user);
	assertThat(JOHN_DOE).isEqualTo(user.getUsername());
    }
    
    @Test
    public void testSaveNewUserWithEmptyUserName() {
	assertThrows(IllegalArgumentException.class, () -> {
	    userService.saveNewUser(User.builder().username(StringUtils.EMPTY).password("password").build());
	});
    }
    
    @Test
    public void testSaveNewUserWithEmptyPassword() {
	assertThrows(IllegalArgumentException.class, () -> {
	    userService.saveNewUser(User.builder().username(JOHN_DOE).password(StringUtils.EMPTY).build());
	});
    }

}
