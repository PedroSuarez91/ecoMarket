package ecomarket.cupon_servicio.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ecomarket.cupon_servicio.model.Cupon;
import ecomarket.cupon_servicio.service.CuponService;

@RestController
@RequestMapping("/api/v1/cupones")
public class CuponController {

    @Autowired
    private CuponService cuponService;

    @GetMapping
    public ResponseEntity<List<Cupon>> getCupones() {
        List<Cupon> cupones = cuponService.findAll();
        if (cupones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cupones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cupon> postCupon(@RequestBody Cupon cupon) {
        try {
            Cupon nuevo = cuponService.save(cupon);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cupon> getCupon(@PathVariable Long id) {
        Cupon buscado = cuponService.findById(id);
        if (buscado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(buscado, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cupon> updateCupon(@PathVariable Long id, @RequestBody Cupon cupon) {
        Cupon actualizado = cuponService.modificar(id, cupon);
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCupon(@PathVariable Long id) {
        try {
            cuponService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}