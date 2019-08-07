/**
 * UserRepository.java
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
package com.sk.sinthoma.user.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.sk.sinthoma.user.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "User")
@Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends MongoRepository<User, String> {

    @ApiOperation("find all Users with given first names and last names")
    List<User> findByFirstNameAndLastName(@Param("firstName") @ApiParam(value = "First Name of the User") String firstName,
	    @Param("lastName") @ApiParam(value = "Last Name of the User") String lastName);
    
    @ApiOperation("find single user with given User ID and Password")
    User findByUserNameAndPassword(@Param("userName") @ApiParam(value = "User Name of the User") String userName,
	    @Param("password") @ApiParam(value = "Password of the User") String password);

}