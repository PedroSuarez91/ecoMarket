package ecomarket.pago_servicio.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ecomarket.pago_servicio.model.BoletaDTO;
import ecomarket.pago_servicio.model.MetodoPago;
import ecomarket.pago_servicio.model.Pago;
import ecomarket.pago_servicio.repository.PagoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${boleta.service.url}")
    private String boletaServiceUrl;

    public Pago iniciarPago(Pago pago) {
        pago.setAceptado(false);
        return pagoRepository.save(pago);
    }

    public Pago seleccionarMetodoPago(Long idPago, MetodoPago nuevoMetodo) {
        Pago pago = pagoRepository.findById(idPago).orElse(null);
        if (pago != null && !pago.isAceptado()) {
            pago.setMetodoPago(nuevoMetodo);
            return pagoRepository.save(pago);
        }
        return null;
    }

    public Pago confirmarMetodoPago(Long idPago) {
        Pago pago = pagoRepository.findById(idPago).orElse(null);
        if (pago == null) return null;

        pago.setAceptado(true);
        Pago pagoConfirmado = pagoRepository.save(pago);

        try {
            BoletaDTO boleta = new BoletaDTO(
                "BOLETA",
                LocalDate.now().toString(),
                pago.getMonto(),
                pago.getMonto(),
                new ArrayList<>()
            );

            String url = boletaServiceUrl + "/api/v1/boletas";
            ResponseEntity<BoletaDTO> respuesta = restTemplate.postForEntity(url, boleta, BoletaDTO.class);

            if (respuesta.getStatusCode().is2xxSuccessful()) {
                System.out.println("Boleta generada exitosamente en boleta_servicio.");
            }
        } catch (Exception e) {
            System.err.println("Advertencia: no se pudo generar la boleta. " + e.getMessage());
        }

        return pagoConfirmado;
    }

    public boolean cancelarMetodoPago(Long idPago) {
        Pago pago = pagoRepository.findById(idPago).orElse(null);
        if (pago != null && !pago.isAceptado()) {
            pagoRepository.delete(pago);
            return true;
        }
        return false;
    }

    public List<Pago> obtenerTodosLosPagos() {
        return pagoRepository.findAll();
    }

    public Pago buscarPorId(Long id) {
        return pagoRepository.findById(id).orElse(null);
    }
}