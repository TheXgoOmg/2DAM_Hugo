package org.dam.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.dam.dto.RecomendacionDTO;
import org.dam.hateoas.RecomendacionHATEOAS;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public ResponseEntity<RecomendacionHATEOAS> showRecomendacionById(@PathVariable Integer idRecomendacion) {
        RecomendacionDTO recomendacionDTO = recomendacionService.getRecomendacionById(idRecomendacion);

        if (recomendacionDTO == null) {
            return ResponseEntity.notFound().build();
        }

        RecomendacionHATEOAS recomendacionHATEOAS = RecomendacionHATEOAS.fromRecomendacionDTO2HATEOAS(recomendacionDTO);

        recomendacionHATEOAS.add(
                linkTo(methodOn(RecomendacionController.class).showRecomendacionById(idRecomendacion))
                        .withSelfRel()
        );

        recomendacionHATEOAS.add(
                linkTo(methodOn(ClienteController.class).listClienteRecomendacion(idRecomendacion))
                        .withRel("Cliente")
        );

        return ResponseEntity.ok(recomendacionHATEOAS);
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

    @GetMapping("/clientes/{idCliente}/recomendacion")
    public ResponseEntity<RecomendacionHATEOAS> listRecomendacionCliente(@PathVariable Integer idCliente) {
        myLog.info(context.getMethod() + " from " + context.getRequestURI());
        RecomendacionDTO laRecomendacion = recomendacionService.listRecomendacionCliente(idCliente);

        if (laRecomendacion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RecomendacionHATEOAS recomendacionHATEOAS = RecomendacionHATEOAS.fromRecomendacionDTO2HATEOAS(laRecomendacion);

        recomendacionHATEOAS.add(
                linkTo(methodOn(ClienteController.class).showClienteById(idCliente))
                        .withRel("Cliente")
        );

        return new ResponseEntity<>(recomendacionHATEOAS, HttpStatus.OK);
    }
}
