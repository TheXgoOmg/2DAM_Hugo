package org.dam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dam.modelo.Cliente;
import org.dam.modelo.Recomendacion;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecomendacionDTO extends RepresentationModel<RecomendacionDTO> {

    private Integer id;
    private String observaciones;
    private Cliente cliente;

    public static RecomendacionDTO convertToDTO(Recomendacion recomendacion) {
        RecomendacionDTO recomendacionDTO = new RecomendacionDTO();

        recomendacionDTO.setId(recomendacion.getId());
        recomendacionDTO.setObservaciones(recomendacion.getObservaciones());
        recomendacionDTO.setCliente(recomendacion.getCliente());
        return recomendacionDTO;
    }

    public static Recomendacion convertToEntity(RecomendacionDTO recomendacionDTO) {
        Recomendacion recomendacion = new Recomendacion();

        recomendacion.setId(recomendacionDTO.getId());
        recomendacion.setObservaciones(recomendacionDTO.getObservaciones());
        recomendacion.setCliente(recomendacionDTO.getCliente());
        return recomendacion;
    }
}
