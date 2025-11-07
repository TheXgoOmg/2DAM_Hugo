package org.dam.Modelo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Mecanico")
public class Mecanico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id_mecanico;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "experiencia_anios")
    private Integer experiencia_anios;

    @Column(name = "taller")
    private String taller;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idChasis",
        foreignKey = @ForeignKey(name = "FK_MEC_CHA"))
    private Chasis chasis;

    @OneToMany(mappedBy = "mecanico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MecanicoMotor> mecanicoMotores = new ArrayList<>();

    @Override
    public String toString() {
        return "Mecanico{" +
                "id_mecanico=" + id_mecanico +
                ", nombre='" + nombre + '\'' +
                ", experiencia_anios=" + experiencia_anios +
                ", taller='" + taller + '\'' +
                ", chasis=" + chasis +
                '}';
    }
}
