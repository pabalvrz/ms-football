package com.pabalvrz.player.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@MappedSuperclass
@Data
public class Person{

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private String name;
    private String lastName;
    private Date bornDate;
    private Integer age;
    private Double height;
    private Double weight;

}
