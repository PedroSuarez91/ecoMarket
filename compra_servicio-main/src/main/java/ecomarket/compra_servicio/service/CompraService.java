package ecomarket.compra_servicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ecomarket.compra_servicio.model.Compra;
import ecomarket.compra_servicio.model.CuponDTO;
import ecomarket.compra_servicio.model.Estado;
import ecomarket.compra_servicio.model.PagoDTO;
import ecomarket.compra_servicio.model.ProductoDTO;
import ecomarket.compra_servicio.repository.CompraRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${producto.service.url}")
    private String productoServiceUrl;

    @Value("${cupon.service.url}")
    private String cuponServiceUrl;

    @Value("${pago.service.url}")
    private String pagoServiceUrl;

    public Compra crearCompra(Compra compra) {
        for (Long productoId : compra.getContenido()) {
            String url = productoServiceUrl + "/api/v1/productos/" + productoId;
            ProductoDTO producto = restTemplate.getForObject(url, ProductoDTO.class);

            if (producto == null) {
                throw new RuntimeException("Producto con ID " + productoId + " no encontrado.");
            }
            if (producto.getStock_tienda() <= 0) {
                throw new RuntimeException("El producto '" + producto.getNombre() + "' no tiene stock disponible.");
            }
        }

        compra.setEstado(Estado.PENDIENTE);
        return compraRepository.save(compra);
    }

    public Compra calcularTotal(Long id) {
        Compra compra = compraRepository.findById(id).orElse(null);
        if (compra == null || compra.getEstado() != Estado.PENDIENTE) return null;

        int suma = 0;
        for (Long productoId : compra.getContenido()) {
            String url = productoServiceUrl + "/api/v1/productos/" + productoId;
            ProductoDTO producto = restTemplate.getForObject(url, ProductoDTO.class);
            if (producto != null) {
                suma += producto.getPrecio();
            }
        }

        compra.setSubtotal(suma);
        compra.setTotal(suma);
        return compraRepository.save(compra);
    }

    public Compra aplicarDescuento(Long compraId, Long cuponId) {
        Compra compra = compraRepository.findById(compraId).orElse(null);
        if (compra == null || compra.getEstado() != Estado.PENDIENTE) return null;

        CuponDTO cupon;
        try {
            String url = cuponServiceUrl + "/api/v1/cupones/" + cuponId;
            cupon = restTemplate.getForObject(url, CuponDTO.class);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Cupón con ID " + cuponId + " no encontrado en cupon_servicio.");
        }

        if (cupon == null) throw new RuntimeException("Cupón no encontrado.");

        int nuevoTotal = compra.getSubtotal();
        if ("FIJO".equalsIgnoreCase(cupon.getTipo())) {
            nuevoTotal = compra.getSubtotal() - cupon.getMonto();
        } else if ("PORCENTAJE".equalsIgnoreCase(cupon.getTipo())) {
            nuevoTotal = (compra.getSubtotal() * (100 - cupon.getMonto())) / 100;
        }

        compra.setTotal(Math.max(0, nuevoTotal));
        return compraRepository.save(compra);
    }

    public Compra confirmarCompra(Long id, String metodoPago) {
        Compra compra = compraRepository.findById(id).orElse(null);
        if (compra == null || compra.getEstado() != Estado.PENDIENTE) return null;

        PagoDTO pagoRequest = new PagoDTO(metodoPago, compra.getTotal(), false);
        String url = pagoServiceUrl + "/api/v1/pagos";
        ResponseEntity<PagoDTO> respuesta = restTemplate.postForEntity(url, pagoRequest, PagoDTO.class);

        if (!respuesta.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al crear el pago en pago_servicio.");
        }

        compra.setEstado(Estado.CONFIRMADA);
        return compraRepository.save(compra);
    }

    public Compra cancelarCompra(Long id) {
        Compra compra = compraRepository.findById(id).orElse(null);
        if (compra != null && compra.getEstado() == Estado.PENDIENTE) {
            compra.setEstado(Estado.CANCELADA);
            return compraRepository.save(compra);
        }
        return null;
    }

    public List<Compra> findAll() {
        return compraRepository.findAll();
    }

    public Compra findById(Long id) {
        return compraRepository.findById(id).orElse(null);
    }
}
