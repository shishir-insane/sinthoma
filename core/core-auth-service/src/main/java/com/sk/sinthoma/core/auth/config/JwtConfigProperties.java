/**
 * JwtConfigProperties.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "app.jwt")

/**
 * Instantiates a new jwt config properties.
 */
@Data
public class JwtConfigProperties {

    private String secretKey = "J5JC9dHp5K";

    // validity in milliseconds
    private long validityInMs = 3600000; // 1h
}