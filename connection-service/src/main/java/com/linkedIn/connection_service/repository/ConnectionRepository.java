package com.linkedIn.connection_service.repository;

import com.linkedIn.connection_service.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConnectionRepository extends MongoRepository<Person, Integer> {
}
