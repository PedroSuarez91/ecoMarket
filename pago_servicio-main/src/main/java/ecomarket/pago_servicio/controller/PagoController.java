package ecomarket.pago_servicio.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ecomarket.pago_servicio.model.MetodoPago;
import ecomarket.pago_servicio.model.Pago;
import ecomarket.pago_servicio.service.PagoService;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;


    @PostMapping
    public ResponseEntity<Pago> registrarIntentoPago(@RequestBody Pago pago) {
        try {
            Pago nuevoPago = pagoService.iniciarPago(pago);
            return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/{id}/seleccionar-metodo")
    public ResponseEntity<Pago> cambiarMetodo(@PathVariable Long id, @RequestParam MetodoPago metodo) {
        Pago actualizado = pagoService.seleccionarMetodoPago(id, metodo);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Pago> confirmar(@PathVariable Long id) {
        Pago confirmado = pagoService.confirmarMetodoPago(id);
        if (confirmado != null) {
            return new ResponseEntity<>(confirmado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelar(@PathVariable Long id) {
        boolean eliminado = pagoService.cancelarMetodoPago(id);
        if (eliminado) {
            return new ResponseEntity<>("Transacción cancelada y removida de forma segura", HttpStatus.OK);
        }
        return new ResponseEntity<>("No se pudo cancelar el pago o ya fue procesado", HttpStatus.BAD_REQUEST);
    }


    @GetMapping
    public ResponseEntity<List<Pago>> listarTodosLosPagos() {
        try {
            List<Pago> pagos = pagoService.obtenerTodosLosPagos();
            if (pagos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
            }
            return new ResponseEntity<>(pagos, HttpStatus.OK); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPagoPorId(@PathVariable Long id) {
        try {
            Pago pago = pagoService.buscarPorId(id);
            if (pago != null) {
                return new ResponseEntity<>(pago, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}