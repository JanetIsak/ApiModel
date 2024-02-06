package com.example.apimodel.kafka;

import com.example.apimodel.domain.ApiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {
    private final KafkaTemplate<String, ApiModel> kafkaTemplate;
    public void send(String topic, ApiModel apiModel){
        kafkaTemplate.send(topic,apiModel);
    }
}
