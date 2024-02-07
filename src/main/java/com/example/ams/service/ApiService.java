package com.example.ams.service;

import com.example.ams.domain.ApiModel;
import com.example.ams.dto.ApiData;
import com.example.ams.kafka.Producer;
import com.example.ams.repository.ApiRepo;
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
        ApiData apiData = APIModelToAPIDataModel(apiModel);
        if (apiModel.getType().equalsIgnoreCase("unavailable")) {
            producer.send("unavailableAPI", apiData);
        } else if (apiModel.getType().equalsIgnoreCase("free")) {
            producer.send("freeAPI", apiData);
        }
        apiRepo.save(apiModel);
    }

//    public void addApi(ApiModel apiModel){
//        apiRepo.save(apiModel);
//    }

    public void updateApi(ApiModel apiModel, String id) {
        Optional<ApiModel> optionalApiModel = apiRepo.findById(id);
        if (optionalApiModel.isPresent()) {
            ApiData apiData = APIModelToAPIDataModel(apiModel);
            if (apiModel.getType().equalsIgnoreCase("unavailable")) {
                producer.send("unavailableAPI", apiData);
            } else if (apiModel.getType().equalsIgnoreCase("free")) {
                producer.send("freeAPI", apiData);
            }
            apiRepo.save(apiModel);
        } else {
            throw new NoSuchElementException("Api with ID: " + id + "not found");
        }
    }

    private ApiData APIModelToAPIDataModel(ApiModel apiModel) {
        apiModel.setType(apiModel.getType().toUpperCase());
        ApiData apiData = new ApiData();
        apiData.setName(apiModel.getName());
        apiData.setType(apiModel.getType());
        apiData.setUrl(apiModel.getEndPoint());
        apiData.setDescription(apiModel.getDescription());
        return apiData;
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
