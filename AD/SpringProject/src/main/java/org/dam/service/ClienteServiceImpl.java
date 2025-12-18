package org.dam.service;

import org.dam.dto.ClienteDTO;
import org.dam.modelo.Cliente;
import org.dam.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
        Cliente cliente = ClienteDTO.convertToEntity(clienteDTO);
        clienteRepository.save(cliente);
        return clienteDTO;
    }

    @Override
    public ClienteDTO getClienteById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(Long.valueOf(id));
        if(cliente.isPresent()) {
            return ClienteDTO.convertToDTO(cliente.get());
        }else  {
            return null;
        }
    }

    @Override
    public List<ClienteDTO> listAllClientes() {
        List<Cliente> lista = clienteRepository.findAll();
        List<ClienteDTO> listaResultado = new ArrayList<>();
        for (int i = 0; i < lista.size(); ++i) {
            listaResultado.add(ClienteDTO.convertToDTO(lista.get(i)));
        }
        return listaResultado;
    }

    @Override
    public void deleteCliente(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(Long.valueOf(id));
        cliente.ifPresent(value -> clienteRepository.delete(value));
    }
}