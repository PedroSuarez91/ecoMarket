package ecomarket.solicitud_microservicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecomarket.solicitud_microservicio.model.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long>{
    
}
