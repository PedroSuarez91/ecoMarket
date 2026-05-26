package ecomarket.solicitud_microservicio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ecomarket.solicitud_microservicio.model.Solicitud;
import ecomarket.solicitud_microservicio.service.SolicitudService;

@RestController
@RequestMapping("/api/v1/solicitud")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @GetMapping
    public ResponseEntity<List<Solicitud>> getSolicitudes() {
        List<Solicitud> solicitudes = solicitudService.findAll();
        if (solicitudes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Solicitud> postSolicitud(@RequestBody Solicitud solicitud) {
        Solicitud nueva;
        try {
            nueva = solicitudService.save(solicitud);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getSolicitud(@PathVariable Long id) {

        Solicitud buscada = solicitudService.findById(id);

        if (buscada == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(buscada, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> updateSolicitud(
            @PathVariable Long id,
            @RequestBody Solicitud solicitud) {

        Solicitud actualizada = solicitudService.modificar(id, solicitud);

        if (actualizada == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Long id) {

        try {
            solicitudService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}