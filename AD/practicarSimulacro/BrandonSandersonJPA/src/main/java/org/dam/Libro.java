package org.dam;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "libro")
@AllArgsConstructor
@NoArgsConstructor

public class Libro {
    @Id
    private Integer codigo;

    private String titulo;

    private Integer idSaga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSaga", referencedColumnName = "idSaga",  insertable = false, updatable = false)
    private Saga saga;
}
