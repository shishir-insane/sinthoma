/**
 * UserRepositoryIntegrationTest.java
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
package com.sk.sinthoma.user.unit.repository;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.sk.sinthoma.user.model.User;
import com.sk.sinthoma.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = { "amazon.dynamodb.endpoint=http://localhost:8000/", "amazon.aws.accesskey=test1",
	"amazon.aws.secretkey=test231" })
@Slf4j
public class UserRepositoryIntegrationTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    UserRepository repository;

    @Before
    public void setup() throws Exception {
	try {
	    dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
	    final CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(User.class);
	    tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
	    amazonDynamoDB.createTable(tableRequest);
	} catch (final ResourceInUseException e) {
	    log.warn("Error in creating tables", e);
	}

	dynamoDBMapper.batchDelete(repository.findAll());
    }

    @org.junit.Test
    public void givenItemWithExpectedCost_whenRunFindAll_thenItemIsFound() {
	final User user = new User();
	user.setFirstName("John");
	user.setLastName("Doe");
	repository.save(user);

	final List<User> result = (List<User>) repository.findAll();
	assertThat(result.size(), is(greaterThan(0)));
	assertThat(result.get(0).getFirstName(), is(equalTo("John")));
    }
}