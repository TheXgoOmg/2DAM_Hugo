package org.dam.hateoas;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dam.dto.CuentaDTO;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CuentaHATEOAS extends RepresentationModel<CuentaDTO> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String banco;
    private String sucursal;
    private String dc;
    private String numeroCuenta;
    private Double saldoActual;

    public static CuentaHATEOAS fromCuentaDTO2HATEOAS(CuentaDTO cuentaDTO) {
        return new CuentaHATEOAS(
                cuentaDTO.getId(),
                cuentaDTO.getBanco(),
                cuentaDTO.getSucursal(),
                cuentaDTO.getDc(),
                cuentaDTO.getNumeroCuenta(),
                cuentaDTO.getSaldoActual()
        );
    }
}
