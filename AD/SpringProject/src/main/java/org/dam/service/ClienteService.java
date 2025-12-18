package org.dam.service;

import org.dam.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    ClienteDTO saveCliente(ClienteDTO clienteDTO);
    ClienteDTO getClienteById(Integer id);
    List<ClienteDTO> listAllClientes();
    void deleteCliente(Integer id);

}