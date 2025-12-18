package org.dam.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.dam.dto.RecomendacionDTO;
import org.dam.service.ClienteService;
import org.dam.service.RecomendacionService;
import org.dam.service.RecomendacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class RecomendacionController {

    private static final Logger myLog = Logger.getLogger(RecomendacionController.class.getName());

    @Autowired
    private RecomendacionService recomendacionService;

    @Autowired
    private HttpServletRequest context;

    @Value("${app.name}")
    private String appName;

    @Value("${developer.name}")
    private String devName;

    @GetMapping("/recomendaciones")
    public List<RecomendacionDTO> listRecomendaciones() {
        myLog.info(context.getMethod() + " from " + context.getRemoteHost());
        return recomendacionService.listAllRecomendaciones();
    }

    @GetMapping("/recomendaciones/{idRecomendacion}")
    public ResponseEntity<RecomendacionDTO> showRecomendacionById(@PathVariable Integer idRecomendacion) {
        myLog.info(context.getMethod() + context.getRequestURI() + " from " + context.getRemoteHost());
        RecomendacionDTO laRecomendacion = recomendacionService.getRecomendacionById(idRecomendacion);
        if (laRecomendacion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(laRecomendacion, HttpStatus.OK);
        }
    }

    @PostMapping("/recomendaciones")
    public ResponseEntity<RecomendacionDTO> addRecomendacion(@RequestBody RecomendacionDTO newRecomendacion) {
        myLog.info(context.getMethod() + context.getRequestURI());
        RecomendacionDTO laRecomendacion = recomendacionService.saveRecomendacion(newRecomendacion);
        if (laRecomendacion == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(laRecomendacion, HttpStatus.OK);
        }
    }

    @PutMapping("/recomendaciones")
    public ResponseEntity<RecomendacionDTO> updateRecomendacion(@RequestBody RecomendacionDTO uptRecomendacion) {
        myLog.info(context.getMethod() + context.getRequestURI());
        RecomendacionDTO laRecomendacion = recomendacionService.getRecomendacionById(uptRecomendacion.getId());
        if (laRecomendacion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(laRecomendacion, HttpStatus.OK);
        }
    }

    @DeleteMapping("/recomendaciones/{idRecomendacion}")
    public ResponseEntity<String> deleteRecomendacion(@PathVariable Integer idRecomendacion) {
        recomendacionService.deleteRecomendacion(idRecomendacion);
        return new ResponseEntity<>("Recomendación borrada satisfactoriamente\n",HttpStatus.OK);
    }
}
