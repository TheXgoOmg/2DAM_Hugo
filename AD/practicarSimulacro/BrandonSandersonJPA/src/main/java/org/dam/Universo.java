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
@Table(name = "universo")
@AllArgsConstructor
@NoArgsConstructor

public class Universo {
    @Id
    private Integer idUniverso;

    private String nombre;

    @OneToMany(mappedBy = "universo",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ToString.Exclude
	private List<Saga> sagas = new ArrayList<>();
}
