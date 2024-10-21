package com.pabalvrz.player.model;

import com.pabalvrz.player.model.enums.Foot;
import com.pabalvrz.player.model.enums.Position;
import com.pabalvrz.player.model.enums.SpecificPosition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player extends Person{

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

}