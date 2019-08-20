package com.sk.sinthoma.bff.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.sk.sinthoma.bff.dashboard.model.User;

@Service
public class DefaultUserManagerService implements UserManagerService {

    @Value("#{'${app.services.user-manager.uri}' + "
    	+ "'${app.services.user-manager.resource.search-by-username}'}")
    private String userManagerSearchResourceUri;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User login(User user) {
	if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
	    throw new IllegalArgumentException("UserName or Password in the request can not be empty.");
	}
	User loggedUser = restTemplate.getForObject(userManagerSearchResourceUri, User.class, user.getUserName());
	if (null == loggedUser || StringUtils.isEmpty(loggedUser.getUserName())) {
	    throw new ResourceNotFoundException("User trying to login is invalid.");
	}
	return loggedUser;
    }

}
