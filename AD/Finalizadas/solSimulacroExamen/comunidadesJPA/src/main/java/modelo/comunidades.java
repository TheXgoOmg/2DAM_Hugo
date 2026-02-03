package modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "comunidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class comunidades {

    @Column(name = "parent_code")
    private Integer parent_code;

    @Id
    @Column(name = "code")
    private Integer code;

    @Column(name = "label")
    private String label;

    @OneToMany(
            mappedBy = "comunidad",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<provincias> provincias = new ArrayList<>();
}