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
package com.sk.sinthoma.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sk.sinthoma.user.model.User;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
	final User user = new User();
	user.setFirstName("John");
	user.setLastName("Doe");
	userRepository.save(user);
    }

    @Test
    void testFindByFirstNameAndLastNameNotNulResult() {
	final List<User> users = userRepository.findByFirstNameAndLastName("John", "Doe");
	assertThat(users).isNotNull();
	assertThat(users.size()).isEqualTo(1);
    }

    @AfterEach
    public void destroy() {
	userRepository.deleteAll();
    }
}