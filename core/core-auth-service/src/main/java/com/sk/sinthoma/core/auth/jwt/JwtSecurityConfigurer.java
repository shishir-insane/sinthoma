/**
 * JwtSecurityConfigurer.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Instantiates a new jwt security configurer.
     *
     * @param jwtTokenProvider the jwt token provider
     */
    public JwtSecurityConfigurer(JwtTokenProvider jwtTokenProvider) {
	this.jwtTokenProvider = jwtTokenProvider;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.config.annotation.SecurityConfigurerAdapter#
     * configure(org.springframework.security.config.annotation.SecurityBuilder)
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
	final JwtTokenAuthenticationFilter customFilter = new JwtTokenAuthenticationFilter(jwtTokenProvider);
	http.exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint()).and()
		.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}