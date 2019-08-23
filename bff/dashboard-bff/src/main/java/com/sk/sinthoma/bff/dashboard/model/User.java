/**
 * User.java
 * dashboard-bff
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
package com.sk.sinthoma.bff.dashboard.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("User")
public class User implements Serializable {

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
