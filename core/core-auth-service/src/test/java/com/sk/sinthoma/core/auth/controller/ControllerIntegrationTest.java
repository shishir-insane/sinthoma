/**
 * ControllerIntegrationTest.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.controller;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.sinthoma.core.auth.model.AuthenticationRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Slf4j
public class ControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    ObjectMapper objectMapper;

    private String token;

    /**
     * Setup.
     */
    @BeforeEach
    public void setup() {
	RestAssured.port = port;
	token = given().contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(new AuthenticationRequest("john.doe", "password")).when().post("/sinthoma/auth/login").andReturn()
		.jsonPath().getString("token");
	log.info("Got token:" + token);
    }

    /**
     * Gets the current user with out auth token.
     *
     * @return the current user with out auth token
     */
    @Test
    public void getCurrentUserWithOutAuthToken() {
	given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get("/sinthoma/auth/me").then()
		.statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    /**
     * Gets the current user with auth token.
     *
     * @return the current user with auth token
     */
    @Test
    public void getCurrentUserWithAuthToken() {
	given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).accept(ContentType.JSON).when()
		.get("/sinthoma/auth/me").then().statusCode(HttpStatus.SC_OK);
    }

}
