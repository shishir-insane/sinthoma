/**
 * DefaultUserDetailsService.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sk.sinthoma.core.auth.model.User;
import com.sk.sinthoma.core.auth.repository.UserAuthRepository;

@Service
public class DefaultUserDetailsService implements UserDetailsService, UserService {

    @Autowired
    private UserAuthRepository repository;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     */
    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
	return findByUsername(userName)
		.orElseThrow(() -> new UsernameNotFoundException("Username: " + userName + " not found"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sk.sinthoma.core.auth.service.UserService#findByUsername(java.lang.
     * String)
     */
    @Override
    public Optional<User> findByUsername(String username) {
	if (StringUtils.isEmpty(username)) {
	    throw new IllegalArgumentException("Username in the request can not be empty.");
	}
	return repository.findByUsername(username);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sk.sinthoma.core.auth.service.UserService#saveNewUser(com.sk.sinthoma.
     * core.auth.model.User)
     */
    @Override
    public User saveNewUser(User user) {
	if ((null == user) || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
	    throw new IllegalArgumentException("Username or password in the request can not be empty.");
	}
	return repository.save(user);
    }
}