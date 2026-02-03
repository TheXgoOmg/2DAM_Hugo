package org.dam.service;

import org.dam.dto.ClienteDTO;
import org.dam.modelo.Cliente;
import org.dam.modelo.Cuenta;
import org.dam.modelo.Direccion;
import org.dam.modelo.Recomendacion;
import org.dam.repository.ClienteRepository;
import org.dam.repository.CuentaRepository;
import org.dam.repository.DireccionRepository;
import org.dam.repository.RecomendacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private RecomendacionRepository recomendacionRepository;

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
    public List<ClienteDTO> listClientesDireccion(Integer idDireccion) {
        Direccion direccion = direccionRepository.findById(Long.valueOf(idDireccion))
                .orElse(null);

        if (direccion == null) {
            return null;
        }

        if (direccion.getClientes() == null || direccion.getClientes().isEmpty()) {
            return new ArrayList<>();
        }

        return direccion.getClientes()
                .stream()
                .map(ClienteDTO::convertToDTO)
                .toList();
    }

    public ClienteDTO listClienteCuenta(Integer idCuenta) {
        Cuenta cuenta = cuentaRepository.findById(Long.valueOf(idCuenta))
                .orElse(null);

        if (cuenta == null) {
            return null;
        }
        if (cuenta.getCliente() == null) {
            return new ClienteDTO();
        }

        return ClienteDTO.convertToDTO(cuenta.getCliente());
    }

    public ClienteDTO listClienteRecomendacion(Integer idRecomendacion) {
        Recomendacion recomendacion = recomendacionRepository.findById(Long.valueOf(idRecomendacion))
                .orElse(null);

        if (recomendacion == null) {
            return null;
        }

        if (recomendacion.getCliente() == null) {
            return new ClienteDTO();
        }

        return ClienteDTO.convertToDTO(recomendacion.getCliente());
    }

    @Override
    public void deleteCliente(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(Long.valueOf(id));
        cliente.ifPresent(value -> clienteRepository.delete(value));
    }
}