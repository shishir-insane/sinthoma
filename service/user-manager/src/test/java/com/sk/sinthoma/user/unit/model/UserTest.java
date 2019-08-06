/**
 * UserTest.java
 * user-manager
 * Copyright 2019 Shishir Kumar
 * 
 * Licensed under the GNU Lesser General Public License v3.0
 * Permissions of this license are conditioned on making available complete 
 * source code of licensed works and modifications under the same license 
 * or the GNU GPLv3. Copyright and license notices must be preserved. 
 * 
 * Contributors provide an express grant of patent rights. However, a larger 
 * work using the licensed work through interfaces provided by the licensed 
 * work may be distributed under different terms and without source code for 
 * the larger work.
 */
package com.sk.sinthoma.user.unit.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.sk.sinthoma.user.model.User;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.EqualsVerifierApi;
import nl.jqno.equalsverifier.Warning;

public class UserTest {

    @Test
    public void equalsContract() {
	EqualsVerifierApi<User> equalsUser = EqualsVerifier.forClass(User.class).suppress(Warning.NONFINAL_FIELDS);
	assertNotNull(equalsUser);
	equalsUser.verify();
    }
}
