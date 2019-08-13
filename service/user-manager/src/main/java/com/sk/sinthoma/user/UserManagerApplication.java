/**
 * UserManagerApplication.java
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
package com.sk.sinthoma.user;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.sk.sinthoma.user.config.DynamoDBConfig;
import com.sk.sinthoma.user.model.User;
import com.sk.sinthoma.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
	DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableDynamoDBRepositories(mappingContextRef = "dynamoDBMappingContext", basePackageClasses = UserRepository.class)
@Configuration
@Import({ DynamoDBConfig.class })
@Slf4j
public class UserManagerApplication {

    public static void main(String[] args) {
	SpringApplication.run(UserManagerApplication.class, args);
    }

    @Bean
    public CommandLineRunner rest(ConfigurableApplicationContext ctx, UserRepository dynamoDBRepository,
	    AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper, DynamoDBMapperConfig config) {
	return (args) -> {

	    CreateTableRequest ctr = dynamoDBMapper.generateCreateTableRequest(User.class)
		    .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
	    TableUtils.createTableIfNotExists(amazonDynamoDB, ctr);
	    TableUtils.waitUntilActive(amazonDynamoDB, ctr.getTableName());

	    createEntities(dynamoDBRepository);

	    log.info("");
	    log.info("Run curl -v http://localhost:8080/users and follow the HATEOS links");
	    log.info("");
	    log.info("Press <enter> to shutdown");
	    System.in.read();
	    ctx.close();
	};
    }

    private void createEntities(UserRepository dynamoDBRepository) {
	User user = new User();
	user.setFirstName("Jack");
	user.setLastName("Daniels");
	dynamoDBRepository.save(user);
	
	user = new User();
	user.setFirstName("Jim");
	user.setLastName("Beam");
	dynamoDBRepository.save(user);

	// fetch all devices
	log.info("Users found with findAll():");
	log.info("-------------------------------");
	for (User userFound : dynamoDBRepository.findAll()) {
	    log.info(userFound.toString());
	}
	log.info("");

    }

}
