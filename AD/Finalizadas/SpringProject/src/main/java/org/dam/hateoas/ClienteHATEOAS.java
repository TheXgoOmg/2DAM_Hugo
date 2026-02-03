package org.dam.hateoas;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dam.dto.ClienteDTO;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ClienteHATEOAS extends RepresentationModel<ClienteDTO> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idCliente;
    private String nif;
    private String nombre;
    private String apellidos;
    private String claveSeguridad;
    private String email;

    public static ClienteHATEOAS fromClienteDTO2HATEOAS (ClienteDTO clienteDTO) {
        return new ClienteHATEOAS(
                clienteDTO.getIdCliente(),
                clienteDTO.getNif(),
                clienteDTO.getNombre(),
                clienteDTO.getApellidos(),
                clienteDTO.getClaveSeguridad(),
                clienteDTO.getEmail());
    }
}
