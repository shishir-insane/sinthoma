package com.sk.sinthoma.user.config;

import org.socialsignin.spring.data.dynamodb.mapping.DynamoDBMappingContext;
import org.socialsignin.spring.data.dynamodb.repository.config.DynamoDBMapperFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

@Configuration
public class DynamoDBConfig {

    @Value("${app.amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${app.amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${app.amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Value("${app.amazon.aws.region}")
    private String amazonAWSRegion;

    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
	return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
	return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
	return DynamoDBMapperConfig.DEFAULT;
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
	return new DynamoDBMapper(amazonDynamoDB, config);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
	return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
		.withRegion(amazonAWSRegion).build();
    }

    @Bean
    public DynamoDBMappingContext dynamoDBMappingContext() {
	return new DynamoDBMappingContext();
    }
}