package ecoMarket.proveedor_microservicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecoMarket.proveedor_microservicio.model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

}
