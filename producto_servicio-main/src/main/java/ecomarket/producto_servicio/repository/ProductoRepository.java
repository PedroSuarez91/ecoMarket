package ecomarket.producto_servicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecomarket.producto_servicio.model.Producto;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
