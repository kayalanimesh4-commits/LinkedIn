package com.linkedIn.connection_service.repository;

import com.linkedIn.connection_service.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person, Integer> {
    Optional<Person> findByName(String name);

    @Query("{ 'userId': ?0 }")
    List<Person> findAllByUserId(String userId);
}
