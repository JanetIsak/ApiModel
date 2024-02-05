package com.example.apimodel.service;

import com.example.apimodel.domain.ApiModel;
import com.example.apimodel.repository.ApiRepo;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ApiService {
    private final ApiRepo apiRepo;

    public ApiService(ApiRepo apiRepo) {
        this.apiRepo = apiRepo;
    }

    public void creatApi(ApiModel apiModel){
        apiRepo.save(apiModel);
    }

//    public void addApi(ApiModel apiModel){
//        apiRepo.save(apiModel);
//    }

    public void updateApi(ApiModel apiModel, String id){
        Optional<ApiModel> optionalApiModel = apiRepo.findById(id);
        if(optionalApiModel.isPresent()){
           apiRepo.save(apiModel);
        } else{
            throw new NoSuchElementException("Api with ID: " + id + "not found");
        }
    }

    public ApiModel getApi(String id){
        Optional<ApiModel> optionalApiModel = apiRepo.findById(id);
        return optionalApiModel.orElse(null);
    }

    public void deleteApi(String id){
        Optional<ApiModel> optionalApiModel = apiRepo.findById(id);
        if(optionalApiModel.isPresent()){
            apiRepo.deleteById(id);
        } else{
            throw new NoSuchElementException("Api with ID: " + id + "not found");
        }
    }
}