package org.dam.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.dam.dto.ClienteDTO;
import org.dam.dto.DireccionDTO;
import org.dam.modelo.Direccion;
import org.dam.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class DireccionController {

    private static final Logger myLog = Logger.getLogger(DireccionController.class.getName());

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private HttpServletRequest context;

    @Value("${app.name}")
    private String appName;

    @Value("${developer.name}")
    private String devName;

    @GetMapping("/direcciones")
    public List<DireccionDTO> listDirecciones() {
        myLog.info(context.getMethod() + " from " + context.getRemoteHost());
        return direccionService.listAllDirecciones();
    }

    @GetMapping("/direcciones/{idDireccion}")
    public ResponseEntity<DireccionDTO> showClienteById(@PathVariable Integer idDireccion) {
        myLog.info(context.getMethod() + context.getRequestURI() + " from " + context.getRemoteHost());
        DireccionDTO laDireccion = direccionService.getDireccionById(idDireccion);
        if (laDireccion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(laDireccion, HttpStatus.OK);
        }
    }

    @PostMapping("/direcciones")
    public ResponseEntity<DireccionDTO> addDireccion(@RequestBody DireccionDTO newDireccion) {
        myLog.info(context.getMethod() + context.getRequestURI());
        DireccionDTO laDireccion = direccionService.saveDireccion(newDireccion);
        if (laDireccion == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(laDireccion, HttpStatus.OK);
        }
    }

    @PutMapping("/direcciones")
    public ResponseEntity<DireccionDTO> updateDireccion(@RequestBody DireccionDTO uptDireccion) {
        myLog.info(context.getMethod() + context.getRequestURI());
        DireccionDTO laDireccion = direccionService.getDireccionById(Math.toIntExact(uptDireccion.getId()));
        if (laDireccion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            DireccionDTO laDireccionUPT = direccionService.saveDireccion(uptDireccion);
            return new ResponseEntity<>(laDireccion, HttpStatus.OK);
        }
    }

    @DeleteMapping("/direcciones/{idDireccion}")
    public ResponseEntity<String> deleteDireccion(@PathVariable Integer idDireccion) {
        direccionService.deleteDireccion(idDireccion);
        return new ResponseEntity<>("Direccion borrada satisfactoriamente\n" ,HttpStatus.OK);

    }
}
