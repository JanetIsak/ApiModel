package com.example.ams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiData {

    private String url;
    private String apiKey;
    private String name;
    private String description;
    private String type;
}
