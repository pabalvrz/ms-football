package com.pabalvrz.player.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private Integer goles;
    private Integer asistencias;
    private Integer partidosJugados;
    private Integer minutosJugados;
    private Integer tarjetasAmarillas;
    private Integer tarjetasRojas;
}
