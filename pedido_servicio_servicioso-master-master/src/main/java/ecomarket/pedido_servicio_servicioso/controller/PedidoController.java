package ecomarket.pedido_servicio_servicioso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ecomarket.pedido_servicio_servicioso.model.Pedido;
import ecomarket.pedido_servicio_servicioso.service.PedidoService;


@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos() {
        List<Pedido> pedidos = pedidoService.findAll();
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pedido> postPedido(@RequestBody Pedido pedido) {
        Pedido nuevo;
        try {
            nuevo = pedidoService.save(pedido);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedido(@PathVariable Long id) {
        Pedido buscado = pedidoService.findById(id);
        if (buscado == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(buscado, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(
            @PathVariable Long id,
            @RequestBody Pedido pedido) {

        Pedido actualizado = pedidoService.modificar(id, pedido);

        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {

        try {
            pedidoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/carro/{idCarro}")
    public ResponseEntity<Pedido> crearDesdeCarro(
            @PathVariable Long idCarro,
            @RequestParam String ubicacion) {

        try {
            Pedido nuevo = pedidoService.crearDesdeCarro(idCarro, ubicacion);

            if (nuevo == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

