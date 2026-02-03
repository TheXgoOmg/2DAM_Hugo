package org.dam.service;

import org.dam.dto.CuentaDTO;

import java.util.List;

public interface CuentaService {

    CuentaDTO saveCuenta(CuentaDTO cuentaDTO);
    CuentaDTO getCuentaById(Integer id);
    List<CuentaDTO> listAllCuentas();
    List<CuentaDTO> listCuentasCliente(Integer idCliente);
    void deleteCuenta(Integer id);

}
