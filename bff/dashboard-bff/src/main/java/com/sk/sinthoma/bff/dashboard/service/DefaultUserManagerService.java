/**
 * DefaultUserManagerService.java
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
package com.sk.sinthoma.bff.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.sk.sinthoma.bff.dashboard.model.User;
import com.sk.sinthoma.core.logger.Loggable;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Loggable
public class DefaultUserManagerService implements UserManagerService {

    @Value("#{'${app.services.user-manager.uri}' + " + "'${app.services.user-manager.resource.search-by-username}'}")
    private String userManagerSearchResourceUri;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User login(User user) {
	if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
	    throw new IllegalArgumentException("UserName or Password in the request can not be empty.");
	}
	log.info("User Manager API Url: {}", userManagerSearchResourceUri);
	final User loggedUser = restTemplate.getForObject(userManagerSearchResourceUri, User.class, user.getUserName());
	if ((null == loggedUser) || StringUtils.isEmpty(loggedUser.getUserName())) {
	    throw new ResourceNotFoundException("User trying to login is invalid.");
	}
	return loggedUser;
    }

}
