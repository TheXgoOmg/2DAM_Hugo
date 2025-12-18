package org.dam.service;

import org.dam.dto.RecomendacionDTO;
import org.dam.modelo.Recomendacion;

import java.util.List;

public interface RecomendacionService {

    RecomendacionDTO saveRecomendacion(RecomendacionDTO recomendacionDTO);
    RecomendacionDTO getRecomendacionById(Integer id);
    List<RecomendacionDTO> listAllRecomendaciones();
    void deleteRecomendacion(Integer id);

}
