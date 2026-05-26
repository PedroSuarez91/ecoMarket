package ecomarket.pedido_servicio_servicioso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecomarket.pedido_servicio_servicioso.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
}
