package ecomarket.cupon_servicio.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecomarket.cupon_servicio.model.Cupon;
import ecomarket.cupon_servicio.repository.CuponRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CuponService {

    @Autowired
    private CuponRepository cuponRepository;

    public Cupon save(Cupon cupon) {
        return cuponRepository.save(cupon);
    }

    public List<Cupon> findAll() {
        return cuponRepository.findAll();
    }

    public Cupon findById(Long id) {
        return cuponRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        cuponRepository.deleteById(id);
    }

    public Cupon modificar(Long id, Cupon cupon) {
        Cupon existente = cuponRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setMonto(cupon.getMonto());
            existente.setTipo(cupon.getTipo());
            return cuponRepository.save(existente);
        }
        return null;
    }
}