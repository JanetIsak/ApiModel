package com.example.apimodel.controller;

import com.example.apimodel.dto.ServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_MANAGER_URL = "http://localhost:8001/client/all-running-services";

    @GetMapping("/running")
    public ServiceData[] getRunningServices() {
        return restTemplate.getForObject(SERVICE_MANAGER_URL, ServiceData[].class);
    }
}
