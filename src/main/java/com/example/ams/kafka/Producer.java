package com.example.ams.kafka;

import com.example.ams.dto.ApiData;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {
    private final KafkaTemplate<String, ApiData> kafkaTemplate;

    public void send(String topic, ApiData apiModel) {
        System.out.println(kafkaTemplate.send(topic, apiModel));
    }
}
