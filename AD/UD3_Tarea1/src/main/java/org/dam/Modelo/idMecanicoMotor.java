package org.dam.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class idMecanicoMotor {

    @Column(name = "id_mecanico")
    private Long id_mecanico;

    @Column(name = "id_motor")
    private Long id_motor;
}
