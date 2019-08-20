package com.sk.sinthoma.bff.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.sk.sinthoma.bff.dashboard.model.User;

public class DefaultUserManagerService implements UserManagerService {
    
    @Value("#{'${app.services.user-manager.uri}' + '${app.services.user-manager.uri.resource.user}'}")
    private String userManagerResourceUri;

    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public User login(String userName, String password) {
	restTemplate.getForObject(userManagerResourceUri, User.class);
	return null;
    }

}
