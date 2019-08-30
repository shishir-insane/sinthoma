/**
 * AuthenticationRequest.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Data

/**
 * Instantiates a new authentication request.
 */
@NoArgsConstructor

/**
 * Instantiates a new authentication request.
 *
 * @param username the username
 * @param password the password
 */
@AllArgsConstructor
public final class AuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -6986746375915710855L;

    private String username;
    private String password;
}