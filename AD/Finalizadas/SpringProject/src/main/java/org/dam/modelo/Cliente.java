package org.dam.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nif")
    private String nif;

    @Column(name = "nombre")
    private String nombre;
    private String apellidos;

    @Column(name = "claveseguridad")
    private String claveSeguridad;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade =  CascadeType.ALL,
            mappedBy = "cliente")
    @ToString.Exclude
    private Recomendacion recomendacion;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "cliente")
    @ToString.Exclude
    private List<Cuenta> listaCuentas;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name= "clientesdirecciones",
            joinColumns=@JoinColumn(name= "idcliente"),
            inverseJoinColumns=@JoinColumn(name= "iddireccion"))
    private List<Direccion> listaDirecciones;



}