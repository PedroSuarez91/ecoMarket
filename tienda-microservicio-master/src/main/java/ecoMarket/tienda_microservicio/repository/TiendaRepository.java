package ecoMarket.tienda_microservicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecoMarket.tienda_microservicio.model.Tienda;

public interface TiendaRepository extends JpaRepository<Tienda, Long> {
}
