package ecomarket.compra_servicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ecomarket.compra_servicio.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}