package org.dam.Modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Mecanico")
public class Mecanico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mecanico;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "experiencia_anio")
    private int experiencia_anio;

    @Column(name = "taller")
    private String taller;

    public Mecanico(Long id_mecanico, String nombre, int experiencia_anio, String taller) {
        this.id_mecanico = id_mecanico;
        this.nombre = nombre;
        this.experiencia_anio = experiencia_anio;
        this.taller = taller;
    }

    public Long getId_mecanico() {
        return id_mecanico;
    }

    public void setId_mecanico(Long id_mecanico) {
        this.id_mecanico = id_mecanico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getExperiencia_anio() {
        return experiencia_anio;
    }

    public void setExperiencia_anio(int experiencia_anio) {
        this.experiencia_anio = experiencia_anio;
    }

    public String getTaller() {
        return taller;
    }

    public void setTaller(String taller) {
        this.taller = taller;
    }
}
