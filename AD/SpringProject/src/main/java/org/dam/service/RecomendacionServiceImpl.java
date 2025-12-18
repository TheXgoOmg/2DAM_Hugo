package org.dam.service;

import org.dam.dto.RecomendacionDTO;
import org.dam.modelo.Recomendacion;
import org.dam.repository.RecomendacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecomendacionServiceImpl implements RecomendacionService {

    @Autowired
    private RecomendacionRepository recomendacionRepository;

    @Override
    public RecomendacionDTO saveRecomendacion(RecomendacionDTO recomendacionDTO) {
        Recomendacion recomendacion = RecomendacionDTO.convertToEntity(recomendacionDTO);
        recomendacionRepository.save(recomendacion);
        return recomendacionDTO;
    }

    @Override
    public RecomendacionDTO getRecomendacionById(Integer id) {
        Optional<Recomendacion> recomendacion = recomendacionRepository.findById(Long.valueOf(id));
        if (recomendacion.isPresent()) {
            return RecomendacionDTO.convertToDTO(recomendacion.get());
        } else {
            return null;
        }
    }

    @Override
    public List<RecomendacionDTO> listAllRecomendaciones() {
        List<Recomendacion> lista = recomendacionRepository.findAll();
        List<RecomendacionDTO> listaResultado = new ArrayList<>();
        for (int i = 0; i < lista.size(); ++i) {
            listaResultado.add(RecomendacionDTO.convertToDTO(lista.get(i)));
        }
        return listaResultado;
    }

    @Override
    public void deleteRecomendacion(Integer id) {
        Optional<Recomendacion> recomendacion = recomendacionRepository.findById(Long.valueOf(id));
        recomendacion.ifPresent(value -> recomendacionRepository.delete(value));
    }
}
