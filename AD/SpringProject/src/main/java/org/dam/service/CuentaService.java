package org.dam.service;

import org.dam.dto.CuentaDTO;

import java.util.List;

public interface CuentaService {

    CuentaDTO saveCuenta(CuentaDTO cuentaDTO);
    CuentaDTO getCuentaById(Integer id);
    List<CuentaDTO> listAllCuentas();
    void deleteCuenta(Integer id);

}
