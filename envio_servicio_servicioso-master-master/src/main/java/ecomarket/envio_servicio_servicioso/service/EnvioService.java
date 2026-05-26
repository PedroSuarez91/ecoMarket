package ecomarket.envio_servicio_servicioso.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ecomarket.envio_servicio_servicioso.model.Envio;
import ecomarket.envio_servicio_servicioso.model.PagoDTO;
import ecomarket.envio_servicio_servicioso.model.PedidoDTO;
import ecomarket.envio_servicio_servicioso.repository.EnvioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${pago.service.url}")
    private String pagoServiceUrl;

    @Value("${pedido.service.url}")
    private String pedidoServiceUrl;

    public Envio save(Envio envio) {
        return envioRepository.save(envio);
    }

    public List<Envio> findAll() {
        return envioRepository.findAll();
    }

    public Envio findById(Long id) {
        return envioRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        envioRepository.deleteById(id);
    }

    public Envio modificar(Long id, Envio envio) {
        Envio existente = envioRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setDireccionEnvio(envio.getDireccionEnvio());
            existente.setFechaEntrega(envio.getFechaEntrega());
            existente.setEstadoEnvio(envio.getEstadoEnvio());
            return envioRepository.save(existente);
        }
        return null;
    }
    public Envio generarEnvio(Long idPago, Long idPedido) {
        PagoDTO pago;
        try {
            String urlPago = pagoServiceUrl + "/api/v1/pagos/" + idPago;
            pago = restTemplate.getForObject(urlPago, PagoDTO.class);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Pago con ID " + idPago + " no encontrado en pago_servicio.");
        }
        if (pago == null) {
            throw new RuntimeException("No se recibió respuesta de pago_servicio.");
        }
        // 2. Verificar que el pago esté aceptado
        if (!pago.isAceptado()) {
            throw new RuntimeException(
                "El pago con ID " + idPago + " aún no ha sido aceptado. No se puede generar el envío.");
        }
        PedidoDTO pedido;
        try {
            String urlPedido = pedidoServiceUrl + "/api/v1/pedidos/" + idPedido;
            pedido = restTemplate.getForObject(urlPedido, PedidoDTO.class);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Pedido con ID " + idPedido + " no encontrado en pedido_servicio.");
        }
        if (pedido == null) {
            throw new RuntimeException("No se recibió respuesta de pedido_servicio.");
        }
        Envio envio = new Envio();
        envio.setDireccionEnvio(pedido.getUbicacion());
        envio.setFechaEntrega(LocalDate.now().plusDays(5).toString());
        envio.setEstadoEnvio("PENDIENTE");

        return envioRepository.save(envio);
    }
}
