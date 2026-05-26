package ecoMarket.tienda_microservicio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ecoMarket.tienda_microservicio.model.EmpleadoDTO;
import ecoMarket.tienda_microservicio.model.Tienda;
import ecoMarket.tienda_microservicio.repository.TiendaRepository;

@Service
public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<String> listarNormas(Long idTienda) {
        Tienda tienda = tiendaRepository.findById(idTienda).orElse(null);

        if (tienda != null) {
            return tienda.getNormas();
        }

        return new ArrayList<>();
    }

    public Tienda crearTienda(Tienda tienda) {

        if (tienda.getNormas() == null) {
            tienda.setNormas(new ArrayList<>());
        }

        if (tienda.getListaEmpleados() == null) {
            tienda.setListaEmpleados(new ArrayList<>());
        }

        return tiendaRepository.save(tienda);
    }

    public List<Tienda> listarTiendas() {
        return tiendaRepository.findAll();
    }

    public Tienda agregarEmpleado(Long idTienda, Long idEmpleado) {
        Tienda tienda = tiendaRepository.findById(idTienda).orElse(null);

        if (tienda != null) {
            String url = "http://localhost:8100/api/v1/empleados/" + idEmpleado;
            EmpleadoDTO empleado = restTemplate.getForObject(url, EmpleadoDTO.class);

            if (empleado != null) {

                if (tienda.getListaEmpleados() == null) {
                    tienda.setListaEmpleados(new ArrayList<>());
                }

                tienda.getListaEmpleados().add(idEmpleado);
                return tiendaRepository.save(tienda);
            }
        }

        return null;
    }

    public Tienda eliminarEmpleado(Long idTienda, Long idEmpleado) {
        Tienda tienda = tiendaRepository.findById(idTienda).orElse(null);

        if (tienda == null) {
            return null;
        }

        if (tienda.getListaEmpleados() == null || tienda.getListaEmpleados().isEmpty()) {
            return null;
        }

        boolean eliminado = tienda.getListaEmpleados().remove(idEmpleado);

        if (!eliminado) {
            return null;
        }

        return tiendaRepository.save(tienda);
    }

    public Tienda modificarTienda(Long idTienda, Tienda tiendaActualizada) {
        Tienda tienda = tiendaRepository.findById(idTienda).orElse(null);

        if (tienda == null) {
            return null;
        }

        if (tiendaActualizada.getNombre() != null) {
            tienda.setNombre(tiendaActualizada.getNombre());
        }

        if (tiendaActualizada.getUbicacion() != null) {
            tienda.setUbicacion(tiendaActualizada.getUbicacion());
        }

        if (tiendaActualizada.getNormas() != null) {
            tienda.setNormas(tiendaActualizada.getNormas());
        }

        if (tiendaActualizada.getHorarioTienda() != null) {
            tienda.setHorarioTienda(tiendaActualizada.getHorarioTienda());
        }

        if (tiendaActualizada.getListaEmpleados() != null) {
            tienda.setListaEmpleados(tiendaActualizada.getListaEmpleados());
        }

        return tiendaRepository.save(tienda);
    }
}
