package org.dam.service;

import org.dam.dto.DireccionDTO;
import org.dam.modelo.Cliente;
import org.dam.modelo.Direccion;
import org.dam.repository.ClienteRepository;
import org.dam.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DireccionServiceImpl implements DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public DireccionDTO saveDireccion(DireccionDTO direccionDTO) {
        Direccion direccion = DireccionDTO.convertToEntity(direccionDTO);
        direccionRepository.save(direccion);
        return direccionDTO;
    }

    @Override
    public DireccionDTO getDireccionById(Integer id) {
        Optional<Direccion> direccion = direccionRepository.findById(Long.valueOf(id));
        if (direccion.isPresent()) {
            return DireccionDTO.convertToDTO(direccion.get());
        } else {
            return null;
        }
    }

    @Override
    public List<DireccionDTO> listAllDirecciones() {
        List<Direccion> lista = direccionRepository.findAll();
        List<DireccionDTO> listaResultado = new ArrayList<>();
        for (int i = 0; i < lista.size(); ++i) {
            listaResultado.add(DireccionDTO.convertToDTO(lista.get(i)));
        }
        return listaResultado;
    }

    @Override
    public List<DireccionDTO> listDireccionesCliente(Integer idCliente) {
        Cliente cliente = clienteRepository.findById(Long.valueOf(idCliente))
                .orElse(null);

        if (cliente == null) {
            return null;
        }
        if (cliente.getListaDirecciones() == null || cliente.getListaDirecciones().isEmpty()) {
            return new ArrayList<>();
        }

        return cliente.getListaDirecciones()
                .stream()
                .map(DireccionDTO::convertToDTO)
                .toList();
    }

    @Override
    public void deleteDireccion(Integer id) {
        Optional<Direccion> direccion = direccionRepository.findById(Long.valueOf(id));
        direccion.ifPresent(value -> direccionRepository.delete(value));
    }

}
