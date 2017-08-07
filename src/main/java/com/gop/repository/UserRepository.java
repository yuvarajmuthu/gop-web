package com.gop.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gop.domain.Person;
import com.gop.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByUserName(String userName);
    public List<User> findByLastName(String lastName);	
}
