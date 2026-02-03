package org.dam.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.dam.dto.CuentaDTO;
import org.dam.hateoas.CuentaHATEOAS;
import org.dam.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CuentaController {
    private static final Logger myLog = Logger.getLogger(CuentaController.class.getName());

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private HttpServletRequest context;

    @Value("${app.name}")
    private String appName;

    @Value("${developer.name}")
    private String devName;

    @GetMapping("/cuentas")
    public List<CuentaDTO> listCuentas() {
        myLog.info(context.getMethod() + " from " + context.getRemoteHost());
        return cuentaService.listAllCuentas();
    }

    @GetMapping("/cuentas/{idCuenta}")
    public ResponseEntity<CuentaHATEOAS> showCuentaById(@PathVariable Integer idCuenta) {
        CuentaDTO cuentaDTO = cuentaService.getCuentaById(idCuenta);
        if (cuentaDTO == null) {
            return ResponseEntity.notFound().build();
        }

        CuentaHATEOAS cuentaHATEOAS = CuentaHATEOAS.fromCuentaDTO2HATEOAS(cuentaDTO);

        cuentaHATEOAS.add(
                linkTo(methodOn(CuentaController.class).showCuentaById(idCuenta))
                        .withSelfRel()
        );

        cuentaHATEOAS.add(
                linkTo(methodOn(ClienteController.class).listClienteCuenta(idCuenta))
                        .withRel("cliente")
        );

        return ResponseEntity.ok(cuentaHATEOAS);
    }

    @PostMapping("/cuentas")
    public ResponseEntity<CuentaDTO> addCuenta(@RequestBody CuentaDTO newCuenta) {
        myLog.info(context.getMethod() + context.getRequestURI());
        CuentaDTO laCuenta = cuentaService.saveCuenta(newCuenta);
        if (laCuenta == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(laCuenta, HttpStatus.OK);
        }
    }

    @PutMapping("/cuentas")
    public ResponseEntity<CuentaDTO> updateCuenta(@RequestBody CuentaDTO uptCuenta) {
        myLog.info(context.getMethod() + context.getRequestURI());
        CuentaDTO laCuenta = cuentaService.getCuentaById(uptCuenta.getId());
        if (laCuenta == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }  else {
            return new ResponseEntity<>(laCuenta, HttpStatus.OK);
        }
    }

    @DeleteMapping("/cuentas/{idCuenta}")
    public ResponseEntity<String> deleteCliente(@PathVariable Integer idCuenta) {
        cuentaService.deleteCuenta(idCuenta);
        return new ResponseEntity<>("Cuenta borrado satisfactoriamente\n", HttpStatus.OK);
    }

    @GetMapping("/clientes/{idCliente}/cuentas")
    public ResponseEntity<List<CuentaHATEOAS>> listCuentasCliente(@PathVariable Integer idCliente) {
        myLog.info(context.getMethod() + " from " + context.getRequestURI());
        List<CuentaDTO> laCuenta = cuentaService.listCuentasCliente(idCliente);
        if (laCuenta == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CuentaHATEOAS> listaHATEOAS = laCuenta.stream()
                .map(laCuentaDTO -> CuentaHATEOAS.fromCuentaDTO2HATEOAS(laCuentaDTO))
                .collect(Collectors.toList());

        listaHATEOAS.stream().forEach(
                cuentaHATEOAS -> cuentaHATEOAS.add(
                        linkTo(methodOn(ClienteController.class).showClienteById(idCliente))
                            .withRel("cliente")
                ));

        return new ResponseEntity<>(listaHATEOAS, HttpStatus.OK);
    }
}
