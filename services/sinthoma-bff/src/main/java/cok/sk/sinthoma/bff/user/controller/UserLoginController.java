/**
 * UserLoginController.java
 * sinthoma-bff
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
package cok.sk.sinthoma.bff.user.controller;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {

    @GetMapping("/user")
    public Principal user(HttpServletRequest request) {
	final String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
	return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
    }
}
