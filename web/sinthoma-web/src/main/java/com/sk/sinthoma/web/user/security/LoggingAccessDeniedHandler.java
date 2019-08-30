/**
 * LoggingAccessDeniedHandler.java - sinthoma-web
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.web.user.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoggingAccessDeniedHandler implements AccessDeniedHandler {

    /* (non-Javadoc)
     * @see org.springframework.security.web.access.AccessDeniedHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.access.AccessDeniedException)
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
	    throws IOException, ServletException {

	final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	if (auth != null) {
	    log.info(auth.getName() + " was trying to access protected resource: " + request.getRequestURI());
	}

	response.sendRedirect(request.getContextPath() + "/access-denied");

    }
}