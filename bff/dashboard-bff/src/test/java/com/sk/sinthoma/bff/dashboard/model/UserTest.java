/**
 * UserTest.java - dashboard-bff
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.bff.dashboard.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.EqualsVerifierApi;
import nl.jqno.equalsverifier.Warning;

public class UserTest {

    /**
     * Equals contract.
     */
    @Test
    public void equalsContract() {
	final EqualsVerifierApi<User> equalsUser = EqualsVerifier.forClass(User.class)
		.suppress(Warning.NONFINAL_FIELDS);
	assertNotNull(equalsUser);
	equalsUser.verify();
    }
}
