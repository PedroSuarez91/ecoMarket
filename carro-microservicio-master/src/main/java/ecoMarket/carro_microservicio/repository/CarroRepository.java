package ecoMarket.carro_microservicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecoMarket.carro_microservicio.model.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}
