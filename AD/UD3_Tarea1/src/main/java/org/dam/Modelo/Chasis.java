package org.dam.Modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Year;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Chasis")
public class Chasis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_chasis;

    @Column(name = "numero_chasis", nullable = false)
    private String numero_chasis;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "serie")
    private String serie;

    @Column(name = "anio", nullable = false)
    private Year anio;

    @Column(name = "color_original")
    private String color_original;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "id_motor",
            referencedColumnName = "id_motor",
            unique = true,
            foreignKey = @ForeignKey(name = "FK_CHA_MOT")
    )
    private Motor motor;

    @Column(name = "id_mecanico")
    private int id_mecanico;


    public Chasis(String numero_chasis, String modelo, String serie, Year anio, String color_original, int id_motor, int id_mecanico) {
        this.numero_chasis = numero_chasis;
        this.modelo = modelo;
        this.serie = serie;
        this.anio = anio;
        this.color_original = color_original;
        this.id_motor = id_motor;
        this.id_mecanico = id_mecanico;
    }

    public Long getId_chasis() {
        return id_chasis;
    }

    public void setId_chasis(Long id_chasis) {
        this.id_chasis = id_chasis;
    }

    public String getNumero_chasis() {
        return numero_chasis;
    }

    public void setNumero_chasis(String numero_chasis) {
        this.numero_chasis = numero_chasis;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Year getAnio() {
        return anio;
    }

    public void setAnio(Year anio) {
        this.anio = anio;
    }

    public String getColor_original() {
        return color_original;
    }

    public void setColor_original(String color_original) {
        this.color_original = color_original;
    }

    public int getId_motor() {
        return id_motor;
    }

    public void setId_motor(int id_motor) {
        this.id_motor = id_motor;
    }

    public int getId_mecanico() {
        return id_mecanico;
    }

    public void setId_mecanico(int id_mecanico) {
        this.id_mecanico = id_mecanico;
    }
}
