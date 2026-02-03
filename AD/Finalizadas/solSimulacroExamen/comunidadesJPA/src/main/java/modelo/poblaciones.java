package modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "poblaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class poblaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_code")
    private Integer town_code;

    // FK hacia provincias.code (INT)
    @Column(name = "parent_code")
    private Integer parent_code;

    @Column(name = "label")
    private String label;

    // FK: poblaciones.parent_code -> provincias.code
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code", referencedColumnName = "code", insertable = false, updatable = false)
    private provincias provincia;

    @Override
    public String toString() {
        return "poblaciones{" +
                "town_code=" + town_code +
                ", parent_code=" + parent_code +
                ", label='" + label + '\'' +
                '}';
    }
}