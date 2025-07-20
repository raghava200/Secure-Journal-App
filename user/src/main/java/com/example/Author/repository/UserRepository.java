package com.example.Author.repository;

import com.example.Author.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
 User findByUserName(String username);
 void deleteByUserName(String username);

}
