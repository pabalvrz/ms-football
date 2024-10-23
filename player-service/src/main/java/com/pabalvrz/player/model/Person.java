package com.pabalvrz.player.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
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
