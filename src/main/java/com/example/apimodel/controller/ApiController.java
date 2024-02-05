package com.example.apimodel.controller;


import com.example.apimodel.domain.ApiModel;
import com.example.apimodel.service.ApiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ApiController {
    private final ApiService apiService;


    public ApiController(ApiService apiService){
        this.apiService = apiService;
    }


    @GetMapping("/api")
    public List<ApiModel> getAllApi(){
        return apiService.getAllApi();
    }

    @GetMapping("api/{id}")
    public ApiModel getApi(@PathVariable String id){
        return apiService.getApi(id);
    }


    @PostMapping("api/create")
    public ApiModel creatApi(@RequestBody ApiModel apiModel){
        apiService.creatApi(apiModel);
        return apiModel;
    }

    @PutMapping("api/update/{id}")
    public ApiModel updateApi(@RequestBody ApiModel apiModel, @PathVariable String id){
        apiService.updateApi(apiModel, id);
        return apiModel;
    }


    @DeleteMapping("api/{id}")
    public String deleteApi(@PathVariable String id){
         apiService.deleteApi(id);
         return "Deleted Successfully";
    }
}
