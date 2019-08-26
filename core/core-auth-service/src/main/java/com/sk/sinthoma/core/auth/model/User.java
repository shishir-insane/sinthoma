/**
 * User.java
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
package com.sk.sinthoma.core.auth.model;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@ApiModel("User")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public final class User implements UserDetails, Serializable {

    private static final long serialVersionUID = -1383662780029565743L;

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String imagePath;

    private Map<String, String> userSettings;

    private Date createdOn;
    private Date lastLoggedOn;
    
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	 return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());

    }
}
