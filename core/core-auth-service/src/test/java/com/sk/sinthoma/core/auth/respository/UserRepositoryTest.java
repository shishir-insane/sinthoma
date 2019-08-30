/**
 * UserRepositoryTest.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.respository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sk.sinthoma.core.auth.model.User;
import com.sk.sinthoma.core.auth.repository.UserAuthRepository;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    private UserAuthRepository userAuthRepository;

    private static final String JANE_DOE = "jane.doe";

    /**
     * Inits the.
     */
    @BeforeEach
    public void init() {
	userAuthRepository.save(User.builder().username(JANE_DOE).build());
    }

    /**
     * Test find by username not nul result.
     */
    @Test
    public void testFindByUsernameNotNulResult() {
	final Optional<User> users = userAuthRepository.findByUsername(JANE_DOE);
	assertThat(users.isPresent()).isTrue();
	assertThat(users.get().getUsername()).isEqualTo(JANE_DOE);
    }

    /**
     * Destroy.
     */
    @AfterEach
    public void destroy() {
	userAuthRepository.deleteAll();
    }
}