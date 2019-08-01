package com.sk.sinthoma.user.model;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class UserTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(User.class).verify();
    }
}
