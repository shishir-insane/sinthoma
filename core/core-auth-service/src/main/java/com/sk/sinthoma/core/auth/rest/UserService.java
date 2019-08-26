/**
 * UserService.java
 * user-manager
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
package com.sk.sinthoma.core.auth.rest;

import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.sk.sinthoma.core.auth.model.User;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

public interface UserService {

    @ApiOperation("find single user with given User ID")
    Optional<User> findByUserName(@Param("userName") @ApiParam(value = "User Name of the User") String userName);

}