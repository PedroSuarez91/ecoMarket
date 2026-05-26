package ecomarket.envio_servicio_servicioso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ecomarket.envio_servicio_servicioso.model.Envio;
import ecomarket.envio_servicio_servicioso.service.EnvioService;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> getEnvios() {
        List<Envio> envios = envioService.findAll();
        if (envios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(envios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Envio> postEnvio(@RequestBody Envio envio) {
        try {
            Envio nuevo = envioService.save(envio);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> getEnvio(@PathVariable Long id) {
        Envio buscado = envioService.findById(id);
        if (buscado == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(buscado, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> updateEnvio(
            @PathVariable Long id,
            @RequestBody Envio envio) {
        Envio actualizado = envioService.modificar(id, envio);
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnvio(@PathVariable Long id) {
        try {
            envioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/generar")
    public ResponseEntity<?> generarEnvio(
            @RequestParam Long idPago,
            @RequestParam Long idPedido) {
        try {
            Envio nuevo = envioService.generarEnvio(idPago, idPedido);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno al generar el envío.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

