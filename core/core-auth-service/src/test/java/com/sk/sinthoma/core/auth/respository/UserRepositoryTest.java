/**
 * UserRepositoryTest.java
 * user-manager
 * Copyright 2019 Shishir Kumar
 *
 * Licensed under the GNU Lesser General Public License v3.0
 * Permissions of this license are conditioned on making available complete
 * source code of licensed works and modifications under the same license
 * or the GNU GPLv3. Copyright and license notices must be preserved.
 *
 * Contributors provide an express grant of patent rights. However, a larger
 * work using the licensed work through interfaces provided by the licensed
 * work may be distributed under different terms and without source code for
 * the larger work.
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

    @BeforeEach
    public void init() {
	userAuthRepository.save(User.builder().username(JANE_DOE).build());
    }

    @Test
    public void testFindByUsernameNotNulResult() {
	final Optional<User> users = userAuthRepository.findByUsername(JANE_DOE);
	assertThat(users.isPresent()).isTrue();
	assertThat(users.get().getUsername()).isEqualTo(JANE_DOE);
    }

    @AfterEach
    public void destroy() {
	userAuthRepository.deleteAll();
    }
}