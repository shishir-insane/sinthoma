/**
 * User.java - dashboard-bff
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.bff.dashboard.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Instantiates a new user.
 */
@Data

/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("User")
public final class User implements Serializable {

    private static final long serialVersionUID = -4136222490310022268L;

    private String userName;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String imagePath;

    private Map<String, String> userSettings;

    private Date createdOn;
    private Date lastLoggedOn;
}
