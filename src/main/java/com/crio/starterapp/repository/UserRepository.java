package com.crio.starterapp.repository;
import com.crio.starterapp.entity.User;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByOrderByScoreDesc();
}
