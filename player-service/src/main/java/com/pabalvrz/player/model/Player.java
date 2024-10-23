package com.pabalvrz.player.model;

import com.pabalvrz.player.model.enums.Foot;
import com.pabalvrz.player.model.enums.Position;
import com.pabalvrz.player.model.enums.SpecificPosition;
import com.pabalvrz.team.model.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PLAYER")
public class Player extends Person {

    @Enumerated(EnumType.STRING)
    private Position position;

    @Enumerated(EnumType.STRING)
    private SpecificPosition specificPosition;

    @Enumerated(EnumType.STRING)
    private Foot foot;

    private Integer numeroCamiseta;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "estadistica_id")
    private Statistic statistic;

    private Long teamId;


    public Player(Long id, String nombre, String lastName, Date bornDate, Integer age,
                  Double height, Double weight,
                  Position position, SpecificPosition specificPosition, Foot foot, Integer numeroCamiseta,
                  Statistic statistic) {
        super(id,nombre,lastName,bornDate,age,height,weight);
        this.position = position;
        this.specificPosition = specificPosition;
        this.foot = foot;
        this.numeroCamiseta = numeroCamiseta;
        this.statistic = statistic;
    }
}