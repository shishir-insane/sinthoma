package com.sk.sinthoma.core.auth.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -6986746375915710855L;

    private String username;
    private String password;
}