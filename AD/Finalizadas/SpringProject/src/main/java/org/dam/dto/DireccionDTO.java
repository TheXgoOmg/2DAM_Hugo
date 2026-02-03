package org.dam.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.dam.modelo.Cliente;
import org.dam.modelo.Direccion;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DireccionDTO extends RepresentationModel<DireccionDTO> {

    private Integer id;
    private String descripcion;
    private String pais;
    private String cp;
    private List<Cliente> clientes;

    public static DireccionDTO convertToDTO(Direccion direccion) {
        DireccionDTO direccionDTO = new DireccionDTO();

        direccionDTO.setId(direccion.getId());
        direccionDTO.setDescripcion(direccion.getDescripcion());
        direccionDTO.setPais(direccion.getPais());
        direccionDTO.setCp(direccion.getCp());
        direccionDTO.setClientes(direccion.getClientes());
        return direccionDTO;
    }

    public static Direccion convertToEntity(DireccionDTO direccionDTO) {
        Direccion direccion = new Direccion();

        direccion.setId(direccionDTO.getId());
        direccion.setDescripcion(direccionDTO.getDescripcion());
        direccion.setPais(direccionDTO.getPais());
        direccion.setCp(direccionDTO.getCp());
        direccion.setClientes(direccionDTO.getClientes());
        return direccion;
    }
}
