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

    @Column(name = "idMecanico")
    private Long idMecanico;

    @Column(name = "idMotor")
    private Long idMotor;
}
