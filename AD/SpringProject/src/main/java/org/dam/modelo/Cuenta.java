package org.dam.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "banco")
    private String banco;

    @Column(name = "sucursal")
    private String sucursal;

    @Column(name = "dc")
    private String dc;

    @Column(name = "numerocuenta")
    private String numeroCuenta;

    @Column(name = "saldoactual")
    private Double saldoActual;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idcliente", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    @JsonIgnore
    private Cliente cliente;

}
