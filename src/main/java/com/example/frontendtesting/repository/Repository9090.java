package com.example.frontendtesting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.frontendtesting.model.Model9090;

@Repository
public interface Repository9090 extends MongoRepository<Model9090,String> {

}
