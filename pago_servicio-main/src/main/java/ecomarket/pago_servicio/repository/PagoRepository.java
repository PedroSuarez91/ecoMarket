package ecomarket.pago_servicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ecomarket.pago_servicio.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
}