package com.sk.sinthoma.core.auth.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.EqualsVerifierApi;
import nl.jqno.equalsverifier.Warning;

public class AuthenticationRequestTest {

    @Test
    public void equalsContract() {
	final EqualsVerifierApi<AuthenticationRequest> equalsUser = EqualsVerifier.forClass(AuthenticationRequest.class)
		.suppress(Warning.NONFINAL_FIELDS);
	assertNotNull(equalsUser);
	equalsUser.verify();
    }

}
