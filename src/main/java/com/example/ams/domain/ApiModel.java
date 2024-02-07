package com.example.ams.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiModel {
    @Id
    private String id;
    private String name;        //name
    private String type;        //status
    private String endPoint;    //url
    private String description; //description
}
