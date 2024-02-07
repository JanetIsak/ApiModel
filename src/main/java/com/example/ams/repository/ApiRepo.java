package com.example.ams.repository;

import com.example.ams.domain.ApiModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApiRepo extends MongoRepository<ApiModel, String> {}

