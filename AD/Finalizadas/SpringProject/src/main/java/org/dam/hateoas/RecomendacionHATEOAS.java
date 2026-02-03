package org.dam.hateoas;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dam.dto.RecomendacionDTO;
import org.dam.modelo.Cliente;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RecomendacionHATEOAS extends RepresentationModel<RecomendacionDTO> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String observaciones;

    public static RecomendacionHATEOAS fromRecomendacionDTO2HATEOAS(RecomendacionDTO recomendacionDTO) {
        return new RecomendacionHATEOAS(
                recomendacionDTO.getId(),
                recomendacionDTO.getObservaciones()
        );
    }
}
