package org.dam.Modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Mecanico_Motor")
@AllArgsConstructor
@NoArgsConstructor
public class MecanicoMotor implements Serializable {
    @EmbeddedId
    private idMecanicoMotor idMecanicoMotor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idMecanico")
    @JoinColumn(name = "idMecanico")
    private Mecanico mecanico;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idMotor")
    @JoinColumn(name = "idMotor")
    private Motor motor;

}
