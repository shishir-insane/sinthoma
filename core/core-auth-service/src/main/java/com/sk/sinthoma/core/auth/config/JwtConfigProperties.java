package com.sk.sinthoma.core.auth.config;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "app.jwt")
@Data
public class JwtConfigProperties {

	private String secretKey = "J5JC9dHp5K";

	//validity in milliseconds
	private long validityInMs = 3600000; // 1h
}