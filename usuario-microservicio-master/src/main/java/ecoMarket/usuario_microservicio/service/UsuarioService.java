package ecoMarket.usuario_microservicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ecoMarket.usuario_microservicio.repository.UsuarioRepository;
import ecoMarket.usuario_microservicio.model.CarroDTO;
import ecoMarket.usuario_microservicio.model.Usuario;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Usuario crear(Usuario usuario) {
        return usuarioRepository.save(usuario);

    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario modificar(Long id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre(usuario.getNombre());
            existente.setEmail(usuario.getEmail());
            existente.setTelefono(usuario.getTelefono());
            existente.setDireccion(usuario.getDireccion());
            existente.setPassword(usuario.getPassword());
            return usuarioRepository.save(existente);
        }
        return null;
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario asignarCarro(Long idUsuario, Long idCarro) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if (usuario != null) {
            String url = "http://localhost:8089/api/v1/carros/" + idCarro;
            CarroDTO carro = restTemplate.getForObject(url, CarroDTO.class);
            if (carro != null) {
                usuario.setIdCarro(carro.getIdCarro());

                return usuarioRepository.save(usuario);
            }
        }
        return null;

    }

    public Usuario desactivar(Long id) {

        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario != null) {
            usuario.setActivo(false);
            return usuarioRepository.save(usuario);
        }

        return null;
    }
}
