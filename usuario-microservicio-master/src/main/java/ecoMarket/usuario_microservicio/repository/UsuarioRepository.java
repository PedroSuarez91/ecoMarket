package ecoMarket.usuario_microservicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecoMarket.usuario_microservicio.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
