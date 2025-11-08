package org.dam.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class idMecanicoMotor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_mecanico")
    private Long id_mecanico;

    @Column(name = "id_motor")
    private Long id_motor;
}
