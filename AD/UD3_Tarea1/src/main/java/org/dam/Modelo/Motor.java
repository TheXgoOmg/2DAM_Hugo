package org.dam.Modelo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Year;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Motor")
public class Motor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_motor;

    @Column(name = "codigo_motor", nullable = false, length = 20)
    private String codigo_motor;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "cilindrada", nullable = false)
    private double cilindrada;

    @Column(name = "anio_fabricacion")
    private Year anio_fabricacion;

    @Column(name = "potencia_hp")
    private String potencia_hp;

    public Motor(Long id_motor, String codigo_motor, String tipo, double cilindrada, Year anio_fabricacion, String potencia_hp) {
        this.id_motor = id_motor;
        this.codigo_motor = codigo_motor;
        this.tipo = tipo;
        this.cilindrada = cilindrada;
        this.anio_fabricacion = anio_fabricacion;
        this.potencia_hp = potencia_hp;
    }

    public Long getId_motor() {
        return id_motor;
    }

    public void setId_motor(Long id_motor) {
        this.id_motor = id_motor;
    }

    public String getCodigo_motor() {
        return codigo_motor;
    }

    public void setCodigo_motor(String codigo_motor) {
        this.codigo_motor = codigo_motor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public Year getAnio_fabricacion() {
        return anio_fabricacion;
    }

    public void setAnio_fabricacion(Year anio_fabricacion) {
        this.anio_fabricacion = anio_fabricacion;
    }

    public String getPotencia_hp() {
        return potencia_hp;
    }

    public void setPotencia_hp(String potencia_hp) {
        this.potencia_hp = potencia_hp;
    }
}
