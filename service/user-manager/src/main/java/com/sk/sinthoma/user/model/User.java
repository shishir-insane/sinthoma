package com.sk.sinthoma.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document
@Data
@EqualsAndHashCode
public class User implements Serializable {

    private static final long serialVersionUID = -1383662780029565743L;
    
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String imagePath;
    
    private Map<String, String> userSettings;
    
    private Date createdOn;
    private Date lastLoggedOn;
}
