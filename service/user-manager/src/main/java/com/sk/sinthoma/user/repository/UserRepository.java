package com.sk.sinthoma.user.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.sk.sinthoma.user.model.User;

@Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByFirstNameAndLastName(@Param("firstName") String firstName, 
	    @Param("lastName") String lastName);

}