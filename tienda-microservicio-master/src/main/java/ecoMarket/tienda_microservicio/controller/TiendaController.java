package ecoMarket.tienda_microservicio.controller;

import ecoMarket.tienda_microservicio.model.Tienda;
import ecoMarket.tienda_microservicio.service.TiendaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @GetMapping("/{idTienda}/normas")
    public List<String> listarNormas(@PathVariable Long idTienda) {
        return tiendaService.listarNormas(idTienda);
    }

    @GetMapping
    public List<Tienda> listarTiendas() {
        return tiendaService.listarTiendas();
    }

    @PostMapping
    public Tienda crearTienda(@RequestBody Tienda tienda) {
        return tiendaService.crearTienda(tienda);
    }

    @PutMapping("/{idTienda}/empleados/{idEmpleado}")
    public ResponseEntity<Tienda> agregarEmpleado(
            @PathVariable Long idTienda,
            @PathVariable Long idEmpleado) {

        Tienda tienda = tiendaService.agregarEmpleado(idTienda, idEmpleado);

        if (tienda == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tienda, HttpStatus.OK);

    }

    @DeleteMapping("/{idTienda}/empleados/{idEmpleado}")
    public ResponseEntity<Tienda> eliminarEmpleado(
            @PathVariable Long idTienda,
            @PathVariable Long idEmpleado) {

        Tienda tienda = tiendaService.eliminarEmpleado(idTienda, idEmpleado);

        if (tienda == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tienda, HttpStatus.OK);
    }

    @PutMapping("/{idTienda}")
    public ResponseEntity<Tienda> modificarTienda(
            @PathVariable Long idTienda,
            @RequestBody Tienda tiendaActualizada) {

        Tienda tienda = tiendaService.modificarTienda(idTienda, tiendaActualizada);

        if (tienda == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tienda, HttpStatus.OK);
    }

}
