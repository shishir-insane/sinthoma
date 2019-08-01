package com.sk.sinthoma.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.sk.sinthoma.user.model.User;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
	User user = new User();
	user.setFirstName("John");
	user.setLastName("Doe");
	userRepository.save(user);
    }

    @Test
    void testFindByFirstNameAndLastNameNotNulResult() {
	List<User> users = userRepository.findByFirstNameAndLastName("John", "Doe");
	assertThat(users).isNotNull();
	assertThat(users.size()).isEqualTo(1);
    }

    @AfterEach
    public void destroy() {
	userRepository.deleteAll();
    }
}