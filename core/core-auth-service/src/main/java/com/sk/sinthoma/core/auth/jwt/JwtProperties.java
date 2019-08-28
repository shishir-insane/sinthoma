package com.sk.sinthoma.core.auth.jwt;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "app.jwt")
@Data
public class JwtProperties {

	private String secretKey = "J5JC9dHp5K";

	//validity in milliseconds
	private long validityInMs = 3600000; // 1h
}