package com.sk.sinthoma.core.auth.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.sk.sinthoma.core.auth.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultUserManagerRestService implements UserService {
    
    @Value("#{'${app.services.user-manager.uri}' + " + "'${app.services.user-manager.resource.search-by-username}'}")
    private String userManagerSearchResourceUri;
    
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Optional<User> findByUserName(String userName) {
	if (StringUtils.isEmpty(userName)) {
	    throw new IllegalArgumentException("UserName in the request can not be empty.");
	}
	final User loggedUser = restTemplate.getForObject(userManagerSearchResourceUri, User.class, userName);
	if ((null == loggedUser) || StringUtils.isEmpty(loggedUser.getUsername())) {
	    log.warn("Username {} trying to login is invalid.", userName);
	    throw new UsernameNotFoundException("User trying to login is invalid.");
	}
	return Optional.of(loggedUser);
    }

}
