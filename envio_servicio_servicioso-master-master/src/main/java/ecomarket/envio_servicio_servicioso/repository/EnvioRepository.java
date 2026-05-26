package ecomarket.envio_servicio_servicioso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecomarket.envio_servicio_servicioso.model.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Long>{
    
}
