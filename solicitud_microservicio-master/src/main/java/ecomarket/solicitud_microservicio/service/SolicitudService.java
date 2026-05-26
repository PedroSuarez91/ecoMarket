package ecomarket.solicitud_microservicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecomarket.solicitud_microservicio.model.Solicitud;
import ecomarket.solicitud_microservicio.repository.SolicitudRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    public Solicitud save(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    public List<Solicitud> findAll() {
        return solicitudRepository.findAll();
    }

    public Solicitud findById(Long id) {
        return solicitudRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        solicitudRepository.deleteById(id);
    }

    public Solicitud modificar(Long id, Solicitud solicitud) {

        Solicitud existente = solicitudRepository.findById(id).orElse(null);

        if (existente != null) {

            existente.setTipo(solicitud.getTipo());
            existente.setContenido(solicitud.getContenido());
            existente.setEstado(solicitud.getEstado());
            existente.setFecha(solicitud.getFecha());

            return solicitudRepository.save(existente);
        }

        return null;
    }
}
