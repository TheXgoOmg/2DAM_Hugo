package org.dam.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dam.modelo.Cliente;
import org.dam.modelo.Cuenta;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CuentaDTO {
    private Integer id;
    private String banco;
    private String sucursal;
    private String dc;
    private String numeroCuenta;
    private Double saldoActual;
    private Cliente cliente;

    public static CuentaDTO convertToDTO(Cuenta cuenta) {
        CuentaDTO cuentaDTO = new CuentaDTO();

        cuentaDTO.setId(cuenta.getId());
        cuentaDTO.setBanco(cuenta.getBanco());
        cuentaDTO.setSucursal(cuenta.getSucursal());
        cuentaDTO.setDc(cuenta.getDc());
        cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDTO.setSaldoActual(cuenta.getSaldoActual());
        cuentaDTO.setCliente(cuenta.getCliente());
        return cuentaDTO;
    }

    public static Cuenta convertToEntity(CuentaDTO cuentaDTO) {
        Cuenta cuenta = new Cuenta();

        cuenta.setId(cuentaDTO.getId());
        cuenta.setBanco(cuentaDTO.getBanco());
        cuenta.setSucursal(cuentaDTO.getSucursal());
        cuenta.setDc(cuentaDTO.getDc());
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setSaldoActual(cuentaDTO.getSaldoActual());
        cuenta.setCliente(cuentaDTO.getCliente());
        return cuenta;
    }
}
