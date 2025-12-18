package org.dam.controller;

import org.dam.dto.ClienteDTO;
import org.dam.service.ClienteService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<ClienteDTO> showClienteById(@PathVariable Integer idCliente) {
        myLog.info(context.getMethod() + context.getRequestURI() + " from " + context.getRemoteHost());
        ClienteDTO elCliente = clienteService.getClienteById(idCliente);
        if (elCliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(elCliente, HttpStatus.OK);
        }
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
}