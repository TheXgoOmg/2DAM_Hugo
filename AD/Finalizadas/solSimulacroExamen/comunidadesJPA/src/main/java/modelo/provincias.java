package modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provincias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class provincias {

    @Column(name = "parent_code")
    private Integer parent_code;

    @Id
    @Column(name = "code")
    private Integer code;

    @Column(name = "label")
    private String label;

    // FK: provincias.parent_code -> comunidades.code (INT)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code", referencedColumnName = "code", insertable = false, updatable = false)
    private comunidades comunidad;

    @OneToMany(mappedBy = "provincia", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<poblaciones> poblaciones = new ArrayList<>();

    @Override
    public String toString() {
        return "provincias{" +
                "parent_code=" + parent_code +
                ", code=" + code +
                ", label='" + label + '\'' +
                '}';
    }
}