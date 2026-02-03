package org.dam.dto;

import org.dam.modelo.Cliente;
import org.dam.modelo.Cuenta;
import org.dam.modelo.Direccion;
import org.dam.modelo.Recomendacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDTO extends RepresentationModel<ClienteDTO> {

    private Integer idCliente;
    private String nif;
    private String nombre;
    private String apellidos;
    private String claveSeguridad;
    private String email;
    private Recomendacion recomendacion;
    private List<Cuenta> listaCuentas;
    private List<Direccion> direcciones;

    public static ClienteDTO convertToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setIdCliente(cliente.getId());
        clienteDTO.setNif(cliente.getNif());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellidos(cliente.getApellidos());
        clienteDTO.setClaveSeguridad(cliente.getClaveSeguridad());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setRecomendacion(cliente.getRecomendacion());
        clienteDTO.setListaCuentas(cliente.getListaCuentas());
        clienteDTO.setDirecciones(cliente.getListaDirecciones());
        return clienteDTO;
    }

    public static Cliente convertToEntity(ClienteDTO clientedto) {
        Cliente cliente = new Cliente();

        cliente.setId(clientedto.getIdCliente());
        cliente.setNif(clientedto.getNif());
        cliente.setNombre(clientedto.getNombre());
        cliente.setApellidos(clientedto.getApellidos());
        cliente.setClaveSeguridad(clientedto.getClaveSeguridad());
        cliente.setEmail(clientedto.getEmail());
        cliente.setRecomendacion(clientedto.getRecomendacion());
        cliente.setListaCuentas(clientedto.getListaCuentas());
        cliente.setRecomendacion(clientedto.getRecomendacion());
        return cliente;
    }
}