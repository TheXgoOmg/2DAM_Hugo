package org.dam.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.dam.dto.CuentaDTO;
import org.dam.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

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
    public ResponseEntity<CuentaDTO> showCuentaById(@PathVariable Integer idCuenta) {
        myLog.info(context.getMethod() + context.getRequestURI() + " from " + context.getRemoteHost());
        CuentaDTO laCuenta = cuentaService.getCuentaById(idCuenta);
        if (laCuenta == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(laCuenta, HttpStatus.OK);
        }
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
}
