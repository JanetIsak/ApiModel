package com.example.apimodel.repository;

import com.example.apimodel.domain.ApiModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApiRepo extends MongoRepository<ApiModel, String> {}

