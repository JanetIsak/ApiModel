package com.example.ams.kafka;

import com.example.ams.domain.ApiModel;
import com.example.ams.dto.ApiData;
import com.example.ams.repository.ApiRepo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public void receiveApiData(@Payload ApiData apiData) {
        try {
            // check if object has the properties of ApiData
            // cast and test if it is an instance of ApiData
//            ApiData transformedApiData = (ApiData) apiData;
//            if (transformedApiData == null) {
//                throw new IllegalArgumentException("Object is not an instance of ApiData");
//            }
//            if (transformedApiData.getName() == null || transformedApiData.getType() == null || transformedApiData.getUrl() == null || transformedApiData.getDescription() == null) {
//                throw new IllegalArgumentException("Object does not have the properties of ApiData");
//            }

//            apiData = transformedApiData;
            ApiModel apiModel = new ApiModel();
            apiModel.setName(apiData.getName());
            apiModel.setType(apiData.getType());
            apiModel.setEndPoint(apiData.getUrl());
            apiModel.setDescription(apiData.getDescription());

            apiRepo.save(apiModel);
        } catch (Exception e) {
            System.out.println("Error processing Kafka message " + e.getMessage());
        }
    }

}
