package ecomarket.compra_servicio.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ecomarket.compra_servicio.model.Compra;
import ecomarket.compra_servicio.service.CompraService;

@RestController
@RequestMapping("/api/v1/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public ResponseEntity<List<Compra>> getAllCompras() {
        List<Compra> compras = compraService.findAll();
        if (compras.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Compra> postCompra(@RequestBody Compra compra) {
        try {
            Compra nueva = compraService.crearCompra(compra);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/calcular-total")
    public ResponseEntity<Compra> calcularTotal(@PathVariable Long id) {
        Compra compraActualizada = compraService.calcularTotal(id);
        if (compraActualizada == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(compraActualizada, HttpStatus.OK);
    }

    @PutMapping("/{id}/aplicar-descuento")
    public ResponseEntity<Compra> aplicarDescuento(
            @PathVariable Long id, 
            @RequestParam Long cuponId) {
            
        try {
            Compra compraActualizada = compraService.aplicarDescuento(id, cuponId);
            if (compraActualizada == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(compraActualizada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Compra> confirmarCompra(
            @PathVariable Long id, 
            @RequestParam String metodoPago) {
            
        try {
            Compra confirmada = compraService.confirmarCompra(id, metodoPago);
            if (confirmada == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(confirmada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Compra> cancelarCompra(@PathVariable Long id) {
        Compra cancelada = compraService.cancelarCompra(id);
        if (cancelada == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cancelada, HttpStatus.OK);
    }
}