package ecomarket.producto_servicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecomarket.producto_servicio.model.Producto;
import ecomarket.producto_servicio.repository.ProductoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    public Producto modificar(Long id, Producto producto) {

        Producto existente = productoRepository.findById(id).orElse(null);

        if (existente != null) {

            existente.setMarca(producto.getMarca());
            existente.setNombre(producto.getNombre());
            existente.setPrecio(producto.getPrecio());
            existente.setCategoria(producto.getCategoria());
            existente.setStock_tienda(producto.getStock_tienda());
            existente.setStock_catalogo(producto.getStock_catalogo());
            existente.setStock_inventario(producto.getStock_inventario());
            existente.setDescription(producto.getDescription());

            return productoRepository.save(existente);
        }

        return null;
    }
}
