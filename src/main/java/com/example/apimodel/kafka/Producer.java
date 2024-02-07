package com.example.apimodel.kafka;

import com.example.apimodel.dto.ApiData;
import jakarta.websocket.SendResult;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class Producer {
    private final KafkaTemplate<String, ApiData> kafkaTemplate;

    public void send(String topic, ApiData apiModel) {
        System.out.println(kafkaTemplate.send(topic, apiModel));
    }
}
