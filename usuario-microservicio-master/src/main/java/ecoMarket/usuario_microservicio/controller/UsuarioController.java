package ecoMarket.usuario_microservicio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecoMarket.usuario_microservicio.service.UsuarioService;
import ecoMarket.usuario_microservicio.model.Usuario;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    public Usuario postUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crear(usuario);
    }

    @GetMapping()
    public List<Usuario> getUsuarios() {
        return usuarioService.listar();
    }

    @PutMapping("{id}")
    public Usuario putUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.modificar(id, usuario);
    }

    @DeleteMapping("{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }

    @PutMapping("/{idUsuario}/carro/{idCarro}")
    public ResponseEntity<Usuario> asignarCarro(
            @PathVariable Long idUsuario,
            @PathVariable Long idCarro) {

        Usuario usuario = usuarioService.asignarCarro(idUsuario, idCarro);

        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(usuario, HttpStatus.OK);

    }

    @PatchMapping("{id}/desactivar")
    public ResponseEntity<Usuario> desactivarUsuario(@PathVariable Long id) {

        Usuario usuarioDesactivado = usuarioService.desactivar(id);

        if (usuarioDesactivado != null) {
            return new ResponseEntity<>(usuarioDesactivado, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
