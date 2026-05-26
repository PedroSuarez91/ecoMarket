package ecomarket.empleado_microservicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecomarket.empleado_microservicio.model.Empleado;
import ecomarket.empleado_microservicio.repository.EmpleadoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmpleadoService {    
    
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Empleado save(Empleado emp){
        return empleadoRepository.save(emp);
    }

    public List<Empleado> findAll(){
        return empleadoRepository.findAll();
    }
    
    public Empleado findById(Long id){
        return empleadoRepository.findById(id).get();
    }

    public void deleteById(Long id){
        empleadoRepository.deleteById(id);
    }

public Empleado modificar(Long id, Empleado empleado) {
    Empleado existente = empleadoRepository.findById(id).orElse(null);

    if (existente != null) {
        existente.setNomb_empleado(empleado.getNomb_empleado());
        existente.setPrimer_apellido(empleado.getPrimer_apellido());
        existente.setSegundo_apellido(empleado.getSegundo_apellido());
        existente.setCargo(empleado.getCargo());
        existente.setSalario(empleado.getSalario());
        existente.setTurno(empleado.getTurno());
        existente.setEmail(empleado.getEmail());
        existente.setTelefono(empleado.getTelefono());

        return empleadoRepository.save(existente);
    }

    return null;
}
}
