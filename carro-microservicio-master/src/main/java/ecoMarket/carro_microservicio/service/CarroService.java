package ecoMarket.carro_microservicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ecoMarket.carro_microservicio.model.Carro;
import ecoMarket.carro_microservicio.model.ProductoDTO;
import ecoMarket.carro_microservicio.repository.CarroRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Carro save(Carro carro) {
        return carroRepository.save(carro);
    }

    public List<Carro> findAll() {
        return carroRepository.findAll();
    }

    public Carro findById(Long id) {
        return carroRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        carroRepository.deleteById(id);
    }

    public Carro agregarProducto(Long idCarro, Long idProducto) {
        Carro carro = carroRepository.findById(idCarro).orElse(null);
        if (carro != null) {
            String url = "http://localhost:9094/api/v1/productos/" + idProducto;
            ProductoDTO producto = restTemplate.getForObject(url, ProductoDTO.class);
            if (producto != null) {
                carro.getListaProductos().add(producto.getId());
                carro.setSubtotal(
                        carro.getSubtotal() + producto.getPrecio());

                int totalConImpuesto = (int) (carro.getSubtotal() * 1.19);

                carro.setTotal(totalConImpuesto);

                return carroRepository.save(carro);
            }
        }
        return null;
    }

    public Carro eliminarProducto(Long idCarro, Long idProducto) {

        Carro carro = carroRepository.findById(idCarro).orElse(null);

        if (carro != null) {

            String url = "http://localhost:9094/api/v1/productos/" + idProducto;

            ProductoDTO producto = restTemplate.getForObject(url, ProductoDTO.class);

            if (producto != null) {

                if (carro.getListaProductos().contains(idProducto)) {

                    carro.getListaProductos().remove(Long.valueOf(idProducto));

                    carro.setSubtotal(
                            carro.getSubtotal() - producto.getPrecio());

                    int totalConImpuesto = (int) (carro.getSubtotal() * 1.19);

                    carro.setTotal(totalConImpuesto);

                    return carroRepository.save(carro);

                }

            }

        }

        return null;

    }

}