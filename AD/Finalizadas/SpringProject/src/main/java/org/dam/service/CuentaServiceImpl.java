package org.dam.service;

import org.dam.dto.ClienteDTO;
import org.dam.dto.CuentaDTO;
import org.dam.modelo.Cliente;
import org.dam.modelo.Cuenta;
import org.dam.repository.ClienteRepository;
import org.dam.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public CuentaDTO saveCuenta(CuentaDTO cuentaDTO) {
        Cuenta cuenta = CuentaDTO.convertToEntity(cuentaDTO);
        cuentaRepository.save(cuenta);
        return cuentaDTO;
    }

    @Override
    public CuentaDTO getCuentaById(Integer id) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(Long.valueOf(id));
        if (cuenta.isPresent()) {
            return CuentaDTO.convertToDTO(cuenta.get());
        } else {
            return null;
        }
    }

    @Override
    public List<CuentaDTO> listAllCuentas() {
        List<Cuenta> lista = cuentaRepository.findAll();
        List<CuentaDTO> listaResultado = new ArrayList<>();
        for (int i = 0; i < lista.size(); ++i) {
            listaResultado.add(CuentaDTO.convertToDTO(lista.get(i)));
        }
        return listaResultado;
    }

    public List<CuentaDTO> listCuentasCliente(Integer id) {
        Cliente cliente = clienteRepository.findById(Long.valueOf(id))
                .orElse(null);

        if (cliente == null) {
            return null;
        }
        if (cliente.getListaCuentas() == null || cliente.getListaCuentas().isEmpty()) {
            return new ArrayList<>();
        }

        return cliente.getListaCuentas()
                .stream()
                .map(CuentaDTO::convertToDTO)
                .toList();
    }

    @Override
    public void deleteCuenta(Integer id) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(Long.valueOf(id));
        cuenta.ifPresent(value -> cuentaRepository.delete(value));
    }
}
