package org.dam.service;

import org.dam.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    ClienteDTO saveCliente(ClienteDTO clienteDTO);
    ClienteDTO getClienteById(Integer id);
    List<ClienteDTO> listAllClientes();
    List<ClienteDTO> listClientesDireccion(Integer idDireccion);
    ClienteDTO listClienteCuenta(Integer idCuenta);
    ClienteDTO listClienteRecomendacion(Integer idRecomendacion);
    void deleteCliente(Integer id);

}