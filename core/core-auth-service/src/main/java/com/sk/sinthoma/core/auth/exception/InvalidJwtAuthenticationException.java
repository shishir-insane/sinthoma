/**
 * InvalidJwtAuthenticationException.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {
    private static final long serialVersionUID = -761503632186596342L;

    /**
     * Instantiates a new invalid jwt authentication exception.
     *
     * @param e the e
     */
    public InvalidJwtAuthenticationException(String e) {
	super(e);
    }
}