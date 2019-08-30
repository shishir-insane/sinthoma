/**
 * JwtTokenFilter.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtTokenFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Instantiates a new jwt token filter.
     *
     * @param jwtTokenProvider the jwt token provider
     */
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
	this.jwtTokenProvider = jwtTokenProvider;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
	    throws IOException, ServletException {
	final String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
	if ((token != null) && jwtTokenProvider.validateToken(token)) {
	    final Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
	    SecurityContextHolder.getContext().setAuthentication(auth);
	}
	filterChain.doFilter(req, res);
    }
}