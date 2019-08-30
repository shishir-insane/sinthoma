/**
 * JwtTokenProvider.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.sk.sinthoma.core.auth.config.JwtConfigProperties;
import com.sk.sinthoma.core.auth.exception.InvalidJwtAuthenticationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
    @Autowired
    JwtConfigProperties jwtConfigProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    private String secretKey;

    /**
     * Inits the.
     */
    @PostConstruct
    protected void init() {
	secretKey = Base64.getEncoder().encodeToString(jwtConfigProperties.getSecretKey().getBytes());
    }

    /**
     * Creates the token.
     *
     * @param username the username
     * @param roles    the roles
     * @return the string
     */
    public String createToken(String username, List<String> roles) {
	final Claims claims = Jwts.claims().setSubject(username);
	claims.put("roles", roles);
	final Date now = new Date();
	final Date validity = new Date(now.getTime() + jwtConfigProperties.getValidityInMs());
	return Jwts.builder()//
		.setClaims(claims)//
		.setIssuedAt(now)//
		.setExpiration(validity)//
		.signWith(SignatureAlgorithm.HS256, secretKey)//
		.compact();
    }

    /**
     * Gets the authentication.
     *
     * @param token the token
     * @return the authentication
     */
    public Authentication getAuthentication(String token) {
	final UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
	return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Gets the username.
     *
     * @param token the token
     * @return the username
     */
    public String getUsername(String token) {
	return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Resolve token.
     *
     * @param req the req
     * @return the string
     */
    public String resolveToken(HttpServletRequest req) {
	final String bearerToken = req.getHeader("Authorization");
	if ((bearerToken != null) && bearerToken.startsWith("Bearer ")) {
	    return bearerToken.substring(7, bearerToken.length());
	}
	return null;
    }

    /**
     * Validate token.
     *
     * @param token the token
     * @return true, if successful
     */
    public boolean validateToken(String token) {
	try {
	    final Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
	    if (claims.getBody().getExpiration().before(new Date())) {
		return false;
	    }
	    return true;
	} catch (JwtException | IllegalArgumentException e) {
	    throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
	}
    }
}