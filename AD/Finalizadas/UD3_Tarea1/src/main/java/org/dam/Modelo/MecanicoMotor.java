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
    @MapsId("id_mecanico")
    @JoinColumn(name = "id_mecanico")
    private Mecanico mecanico;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id_motor")
    @JoinColumn(name = "id_motor")
    private Motor motor;

    public MecanicoMotor(Mecanico mecanico, Motor motor) {
        this.idMecanicoMotor = new idMecanicoMotor();
        this.mecanico = mecanico;
        this.motor = motor;
    }
}
