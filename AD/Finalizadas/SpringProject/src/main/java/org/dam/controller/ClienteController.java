package org.dam.controller;

import org.dam.dto.ClienteDTO;
import org.dam.hateoas.ClienteHATEOAS;
import org.dam.modelo.Cliente;
import org.dam.service.ClienteService;
import jakarta.servlet.http.HttpServletRequest;
import org.dam.service.RecomendacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ClienteController {

    private static final Logger myLog = Logger.getLogger(ClienteController.class.getName());

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private HttpServletRequest context;

    @Value("${app.name}")
    private String appName;

    @Value("${developer.name}")
    private String devName;

    @GetMapping("/")
    public String index() {
        String res = "Hola desde Spring\n";
        res += "Estas ejecutando " + appName + "\n";
        res += ". Estando desarrollado por " + devName + "\n";
        return res;
    }

    @GetMapping("/clientes")
    public List<ClienteDTO> listClientes() {
        myLog.info(context.getMethod() + " from " + context.getRemoteHost());
        return clienteService.listAllClientes();
    }

    @GetMapping("/clientes/{idCliente}")
    public ResponseEntity<ClienteHATEOAS> showClienteById(@PathVariable Integer idCliente) {
        ClienteDTO clienteDTO = clienteService.getClienteById(idCliente);
        if (clienteDTO == null) {
            return ResponseEntity.notFound().build();
        }

        ClienteHATEOAS clienteHATEOAS = ClienteHATEOAS.fromClienteDTO2HATEOAS(clienteDTO);

        clienteHATEOAS.add(
                linkTo(methodOn(ClienteController.class).showClienteById(idCliente))
                        .withSelfRel()
        );

        clienteHATEOAS.add(
                linkTo(methodOn(RecomendacionController.class).listRecomendacionCliente(idCliente))
                        .withRel("Recomendacion")
        );

        clienteHATEOAS.add(
                linkTo(methodOn(DireccionController.class).listDireccionesCliente(idCliente))
                        .withRel("listaDirecciones")
        );

        clienteHATEOAS.add(
                linkTo(methodOn(CuentaController.class).listCuentasCliente(idCliente))
                        .withRel("listaCuentas")
        );

        return ResponseEntity.ok(clienteHATEOAS);
    }

    //@PostMapping(value="/clientes",consumes={"application/json"})
    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> addDirector(@RequestBody ClienteDTO newCliente) {
        myLog.info(context.getMethod() + context.getRequestURI());
        ClienteDTO elCliente= clienteService.saveCliente(newCliente);
        if (elCliente==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(elCliente,HttpStatus.OK);
    }

    @PutMapping("/clientes")
    public ResponseEntity<ClienteDTO> updateCliente(@RequestBody ClienteDTO updCliente) {
        myLog.info(context.getMethod() + context.getRequestURI());
        // buscamos si existe previamente
        ClienteDTO elCliente= clienteService.getClienteById(Math.toIntExact(updCliente.getIdCliente()));
        if (elCliente==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
        // como ya sabemos que existe, save actualiza
            ClienteDTO elClienteUPD= clienteService.saveCliente(updCliente);
            return new ResponseEntity<>(elClienteUPD,HttpStatus.OK);
        }
    }

    @DeleteMapping("/clientes/{idCliente}")
    public ResponseEntity<String> deleteCliente(@PathVariable Integer idCliente){
        clienteService.deleteCliente(idCliente);
        return new ResponseEntity<>("Cliente borrado satisfactoriamente\n", HttpStatus.OK);
    }

    @GetMapping("/direcciones/{idDireccion}/clientes")
    public ResponseEntity<List<ClienteHATEOAS>> listClientesDireccion(@PathVariable Integer idDireccion) {
        myLog.info(context.getMethod() + " from " + context.getRequestURI());
        List<ClienteDTO> elCliente = clienteService.listClientesDireccion(idDireccion);
        if (elCliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ClienteHATEOAS> listaHATEOAS = elCliente.stream()
                .map(clienteHATEOAS -> ClienteHATEOAS.fromClienteDTO2HATEOAS(clienteHATEOAS))
                .collect(Collectors.toList());

        listaHATEOAS.stream().forEach(clienteHATEOAS ->
                clienteHATEOAS.add(
                        linkTo(methodOn(DireccionController.class).showDireccionById(idDireccion))
                        .withRel("direccion")
        ));

        return new ResponseEntity<>(listaHATEOAS, HttpStatus.OK);
    }

    @GetMapping("/cuentas/{idCuenta}/cliente")
    public ResponseEntity<ClienteHATEOAS> listClienteCuenta(@PathVariable Integer idCuenta) {
        myLog.info(context.getMethod() + " from " + context.getRequestURI());
        ClienteDTO elCliente = clienteService.listClienteCuenta(idCuenta);
        if (elCliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ClienteHATEOAS clienteHATEOAS = ClienteHATEOAS.fromClienteDTO2HATEOAS(elCliente);

        clienteHATEOAS.add(
                linkTo(methodOn(CuentaController.class).showCuentaById(idCuenta))
                    .withRel("cuenta")
        );

        return new ResponseEntity<>(clienteHATEOAS, HttpStatus.OK);
    }

    @GetMapping("/recomendaciones/{idRecomendacion}/cliente")
    public ResponseEntity<ClienteHATEOAS> listClienteRecomendacion(@PathVariable Integer idRecomendacion) {
        myLog.info(context.getMethod() + " from " + context.getRequestURI());
        ClienteDTO elCliente = clienteService.listClienteRecomendacion(idRecomendacion);
        if (elCliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ClienteHATEOAS clienteHATEOAS = ClienteHATEOAS.fromClienteDTO2HATEOAS(elCliente);

        clienteHATEOAS.add(
                linkTo(methodOn(RecomendacionController.class).showRecomendacionById(idRecomendacion))
                    .withRel("recomendacion")
        );

        return new ResponseEntity<>(clienteHATEOAS, HttpStatus.OK);
    }
}