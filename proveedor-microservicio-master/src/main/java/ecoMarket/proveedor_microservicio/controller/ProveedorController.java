package ecoMarket.proveedor_microservicio.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecoMarket.proveedor_microservicio.model.Proveedor;
import ecoMarket.proveedor_microservicio.service.ProveedorService;
@RestController
@RequestMapping("api/v1/proveedores")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @PostMapping()
    public Proveedor postProveedor(@RequestBody Proveedor proveedor){
        return proveedorService.crear(proveedor);
    }

    @GetMapping()
    public List<Proveedor> getProveedores() {
        return proveedorService.listar();
    }

    @PutMapping("{id}")
    public Proveedor putProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        return proveedorService.modificar(id,proveedor);
    }

    @DeleteMapping("{id}")
    public void deleteProveedor (@PathVariable Long id) {
        proveedorService.eliminar(id);
    }

}


