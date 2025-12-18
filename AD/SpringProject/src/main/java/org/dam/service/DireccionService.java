package org.dam.service;

import org.dam.dto.DireccionDTO;

import java.util.List;

public interface DireccionService {

    DireccionDTO saveDireccion(DireccionDTO direccionDTO);
    DireccionDTO getDireccionById(Integer id);
    List<DireccionDTO> listAllDirecciones();
    void deleteDireccion(Integer id);

}
