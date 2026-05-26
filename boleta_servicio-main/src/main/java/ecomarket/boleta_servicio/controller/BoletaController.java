package ecomarket.boleta_servicio.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ecomarket.boleta_servicio.model.BoletaFactura;
import ecomarket.boleta_servicio.service.BoletaService;

@RestController
@RequestMapping("/api/v1/boletas")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    @GetMapping
    public ResponseEntity<List<BoletaFactura>> getAll() {
        List<BoletaFactura> lista = boletaService.findAll();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BoletaFactura> create(@RequestBody BoletaFactura doc) {
        try {
            BoletaFactura nuevoDoc = boletaService.generarDocumento(doc);
            return new ResponseEntity<>(nuevoDoc, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}/imprimir")
    public ResponseEntity<String> imprimir(@PathVariable Long id) {
        String mensaje = boletaService.imprimirDocumento(id);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PostMapping("/{id}/enviar")
    public ResponseEntity<String> enviar(@PathVariable Long id, @RequestParam String email) {
        boolean enviado = boletaService.enviarPorEmail(id, email);
        if (enviado) {
            return new ResponseEntity<>("Email enviado con éxito", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al enviar o documento inexistente", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/{id}/exportar")
    public ResponseEntity<String> exportar(@PathVariable Long id) {
        String nombreArchivo = boletaService.exportarBoleta(id);
        if (nombreArchivo != null) {
            return new ResponseEntity<>("Documento descargado con éxito: " + nombreArchivo, HttpStatus.OK);
        }
        return new ResponseEntity<>("No se encontró el documento para exportar", HttpStatus.NOT_FOUND);
    }
}