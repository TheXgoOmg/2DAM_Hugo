package org.dam.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Mecanico_Motor")
public class Mecanico_Motor implements Serializable {
    @Id
    @Column(name = "id_mecanico", nullable = false)
    private int id_mecanico;

    @Id
    @Column(name = "id_motor", nullable = false)
    private int id_motor;

    @Column(name = "fecha_certificacion")
    private Date fecha_certificacion;

    public Mecanico_Motor(int id_mecanico, int id_motor, Date fecha_certificacion) {
        this.id_mecanico = id_mecanico;
        this.id_motor = id_motor;
        this.fecha_certificacion = fecha_certificacion;
    }

    public int getId_mecanico() {
        return id_mecanico;
    }

    public void setId_mecanico(int id_mecanico) {
        this.id_mecanico = id_mecanico;
    }

    public int getId_motor() {
        return id_motor;
    }

    public void setId_motor(int id_motor) {
        this.id_motor = id_motor;
    }

    public Date getFecha_certificacion() {
        return fecha_certificacion;
    }

    public void setFecha_certificacion(Date fecha_certificacion) {
        this.fecha_certificacion = fecha_certificacion;
    }
}
