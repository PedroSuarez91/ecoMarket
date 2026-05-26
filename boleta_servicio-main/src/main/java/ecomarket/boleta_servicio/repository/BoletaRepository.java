package ecomarket.boleta_servicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ecomarket.boleta_servicio.model.BoletaFactura;

@Repository
public interface BoletaRepository extends JpaRepository<BoletaFactura, Long> {
}