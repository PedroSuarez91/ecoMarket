package ecomarket.empleado_microservicio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ecomarket.empleado_microservicio.model.Empleado;
import ecomarket.empleado_microservicio.service.EmpleadoService;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> getEmpleados() {
        List<Empleado> empleados = empleadoService.findAll();
        if (empleados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Empleado> postEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevo;
        try {
            nuevo = empleadoService.save(empleado);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable Long id) {
        Empleado buscado = empleadoService.findById(id);
        if (buscado == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(buscado, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(
            @PathVariable Long id,
            @RequestBody Empleado empleado) {
        Empleado actualizado = empleadoService.modificar(id, empleado);
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        try {
            empleadoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}