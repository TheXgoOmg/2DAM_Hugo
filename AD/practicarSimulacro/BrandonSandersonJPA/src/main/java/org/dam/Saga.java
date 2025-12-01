package org.dam;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "saga")
@AllArgsConstructor
@NoArgsConstructor

public class Saga {
    @Id
    private Integer idSaga;

    private String titulo;

    private Integer idUniverso;

    @OneToMany(mappedBy = "saga", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @ToString.Exclude
	private List<Libro> libros = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUniverso", referencedColumnName = "idUniverso",  insertable = false, updatable = false)
    private Universo universo;
}
