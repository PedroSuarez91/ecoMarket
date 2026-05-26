package ecoMarket.proveedor_microservicio.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecoMarket.proveedor_microservicio.model.Proveedor;
import ecoMarket.proveedor_microservicio.repository.ProveedorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    public Proveedor crear(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public List<Proveedor> listar() {
        return proveedorRepository.findAll();
    }

    public Proveedor modificar(Long id, Proveedor proveedor) {
        Proveedor existente=proveedorRepository.findById(id).orElse(null);
        if (existente!=null){
            existente.setNombre(proveedor.getNombre());
            existente.setEmail(proveedor.getEmail());
            existente.setTelefono(proveedor.getTelefono());
            existente.setDireccion(proveedor.getDireccion());
            
            return proveedorRepository.save(existente);
            

            
        }
        return null;
    }
    
    public void eliminar(Long id) {
        proveedorRepository.deleteById(id);
       
    }
}
    

