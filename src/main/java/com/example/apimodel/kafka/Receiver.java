package com.example.apimodel.kafka;

import com.example.apimodel.domain.ApiModel;
import com.example.apimodel.dto.ApiData;
import com.example.apimodel.repository.ApiRepo;
import lombok.extern.java.Log;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Component
public class Receiver {
    private final KafkaTemplate kafkaTemplate;
    private final ApiRepo apiRepo;
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(Receiver.class);


    public Receiver(KafkaTemplate kafkaTemplate, ApiRepo apiRepo) {
        this.kafkaTemplate = kafkaTemplate;
        this.apiRepo = apiRepo;
    }

    //    @KafkaListener(topics = {"${spring.kafka.consumer.topic1}", "${spring.kafka.consumer.topic2}"}, groupId = "${spring.kafka.consumer.group-id}")
//    public void receiveApiData(@Payload ApiData apiData){
//        ApiModel apiModel = new ApiModel();
//        apiModel.setName(apiData.getName());
//        apiModel.setType(apiData.getStatus());
//        apiModel.setEndPoint(apiData.getUrl());
//        apiModel.setDescription(apiData.getDescription());
//        apiRepo.save(apiModel);
//
//    }
    @KafkaListener(topics = {"${spring.kafka.consumer.topic1}", "${spring.kafka.consumer.topic2}"}, groupId = "${spring.kafka.consumer.group-id}")
    @Transactional
    public void receiveApiData(@Payload ApiModel apiData) {
        try {
            ApiModel apiModel = new ApiModel();
            apiModel.setName(apiData.getName());
            apiModel.setType(apiData.getType());
            apiModel.setEndPoint(apiData.getEndPoint());
            apiModel.setDescription(apiData.getDescription());
            apiRepo.save(apiModel);
        } catch (Exception e) {
            System.out.println("Error processing Kafka message " + e.getMessage());
        }
    }

}
