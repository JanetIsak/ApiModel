package com.example.apimodel.service;

import com.example.apimodel.domain.ApiModel;
import com.example.apimodel.kafka.Producer;
import com.example.apimodel.repository.ApiRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiService {
    private final ApiRepo apiRepo;
    private final Producer producer;

    public void creatApi(ApiModel apiModel) {
        apiModel.setType(apiModel.getType().toUpperCase());
        if (apiModel.getType().equalsIgnoreCase("unavailable")) {
            producer.send("unavailableAPI", apiModel);
        } else if (apiModel.getType().equalsIgnoreCase("free")) {
            producer.send("freeAPI", apiModel);
        }
        apiRepo.save(apiModel);
    }

//    public void addApi(ApiModel apiModel){
//        apiRepo.save(apiModel);
//    }

    public void updateApi(ApiModel apiModel, String id) {
        Optional<ApiModel> optionalApiModel = apiRepo.findById(id);
        if (optionalApiModel.isPresent()) {
            apiModel.setType(apiModel.getType().toUpperCase());
            if (apiModel.getType().equalsIgnoreCase("unavailable")) {
                producer.send("unavailableAPI", apiModel);
            } else if (apiModel.getType().equalsIgnoreCase("free")) {
                producer.send("freeAPI", apiModel);
            }
            apiRepo.save(apiModel);
        } else {
            throw new NoSuchElementException("Api with ID: " + id + "not found");
        }
    }

    public List<ApiModel> getAllApi() {
        return apiRepo.findAll();
    }

    public ApiModel getApi(String id) {
        Optional<ApiModel> optionalApiModel = apiRepo.findById(id);
        return optionalApiModel.orElse(null);
    }

    public void deleteApi(String id) {
        Optional<ApiModel> optionalApiModel = apiRepo.findById(id);
        if (optionalApiModel.isPresent()) {
            apiRepo.deleteById(id);
        } else {
            throw new NoSuchElementException("Api with ID: " + id + "not found");
        }
    }
}
