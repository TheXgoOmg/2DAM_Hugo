package org.dam.Modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Year;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Chasis")
public class Chasis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
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
            foreignKey = @ForeignKey(name = "FK_CHA_MOT"))
    private Motor motor;

    @OneToMany(mappedBy = "chasis",
        cascade = CascadeType.PERSIST,
        fetch = FetchType.LAZY)
    private Set<Mecanico> mecanicos;

    public Chasis(Long id_chasis) {
        this.id_chasis = id_chasis;
    }

    @Override
    public String toString() {
        return "Chasis{" +
                "id_chasis=" + id_chasis +
                ", numero_chasis='" + numero_chasis + '\'' +
                ", modelo='" + modelo + '\'' +
                ", serie='" + serie + '\'' +
                ", anio=" + anio +
                ", color_original='" + color_original + '\'' +
                '}';
    }
}
