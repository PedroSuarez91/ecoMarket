package ecomarket.empleado_microservicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecomarket.empleado_microservicio.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
    
}
