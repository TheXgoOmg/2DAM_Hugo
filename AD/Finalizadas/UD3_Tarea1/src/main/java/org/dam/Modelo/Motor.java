package org.dam.Modelo;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Motor")
public class Motor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
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

    @OneToOne(mappedBy = "motor")
    private Chasis chasis;

    @OneToMany(mappedBy = "motor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MecanicoMotor> mecanicoMotores = new ArrayList<>();

    @Override
    public String toString() {
        return "Motor{" +
                "id_motor=" + id_motor +
                ", codigo_motor='" + codigo_motor + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cilindrada=" + cilindrada +
                ", anio_fabricacion=" + anio_fabricacion +
                ", potencia_hp='" + potencia_hp + '\'' +
                ", mecanicoMotores=" + mecanicoMotores +
                '}';
    }
}
