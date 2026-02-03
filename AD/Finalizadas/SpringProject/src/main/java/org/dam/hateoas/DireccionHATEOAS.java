package org.dam.hateoas;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dam.dto.DireccionDTO;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class DireccionHATEOAS extends RepresentationModel<DireccionDTO> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String descripcion;
    private String pais;
    private String cp;

    public static DireccionHATEOAS fromDireccionDTO2HATEOAS(DireccionDTO direccionDTO) {
        return new DireccionHATEOAS(
                direccionDTO.getId(),
                direccionDTO.getDescripcion(),
                direccionDTO.getPais(),
                direccionDTO.getCp());
    }
}
