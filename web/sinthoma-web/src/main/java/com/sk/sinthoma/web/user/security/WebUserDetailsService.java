package com.sk.sinthoma.web.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

public class WebUserDetailsService implements UserDetailsService {
    
    @Value("#{'${app.services.user-manager.uri}' + " + "'${app.services.user-manager.resource.search-by-username}'}")
    private String authServiceLoginUrl;
    
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// TODO Auto-generated method stub
	return null;
    }

}
