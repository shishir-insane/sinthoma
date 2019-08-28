package com.sk.sinthoma.core.auth.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sk.sinthoma.core.auth.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultUserManagerRestService implements UserService {

    @Value("#{'${app.services.user-manager.uri}' + " + "'${app.services.user-manager.resource.search-by-username}'}")
    private String userManagerSearchResourceUri;

    @Value("#{'${app.services.user-manager.uri}' + " + "'${app.services.user-manager.resource.user}'}")
    private String userManagerResourceUri;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Optional<User> findByUserName(String userName) {
	User user = null;
	if (StringUtils.isEmpty(userName)) {
	    throw new IllegalArgumentException("UserName in the request can not be empty.");
	}
	try {
	    user = restTemplate.getForObject(userManagerSearchResourceUri, User.class, userName);
	} catch (RestClientException e) {
	   log.warn("Error while fetching details for requested user '{}'.", userName, e);
	}
	return Optional.ofNullable(user);
    }

    @Override
    public User saveNewUser(User user) {
	if (null == user || StringUtils.isEmpty(user.getUsername())) {
	    throw new IllegalArgumentException("User object or UserName in the request can not be empty.");
	}
	return restTemplate.postForObject(userManagerResourceUri, user, User.class);
    }

}
