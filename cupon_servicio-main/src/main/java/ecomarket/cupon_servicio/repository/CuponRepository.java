package ecomarket.cupon_servicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ecomarket.cupon_servicio.model.Cupon;

@Repository
public interface CuponRepository extends JpaRepository<Cupon, Long> {
}