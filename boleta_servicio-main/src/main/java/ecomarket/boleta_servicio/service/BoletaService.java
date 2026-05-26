package ecomarket.boleta_servicio.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecomarket.boleta_servicio.model.BoletaFactura;
import ecomarket.boleta_servicio.repository.BoletaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    public BoletaFactura generarDocumento(BoletaFactura documento) {
        return boletaRepository.save(documento);
    }

    public List<BoletaFactura> findAll() {
        return boletaRepository.findAll();
    }

    public BoletaFactura findById(Long id) {
        return boletaRepository.findById(id).orElse(null);
    }

    public String imprimirDocumento(Long id) {
        BoletaFactura doc = findById(id);
        if (doc != null) {
            return "Imprimiendo " + doc.getTipo() + " ID: " + doc.getIdGeneral() + " por un total de $" + doc.getTotal();
        }
        return "Documento no encontrado";
    }

    public boolean enviarPorEmail(Long id, String email) {
        BoletaFactura doc = findById(id);
        if (doc != null) {
            System.out.println("Enviando correo con el documento " + doc.getIdGeneral() + " a: " + email);
            return true;
        }
        return false;
    }

    public String exportarBoleta(Long id) {
        BoletaFactura doc = findById(id);
        if (doc != null) {
            return doc.getTipo() + "_" + doc.getIdGeneral() + "_exportado.pdf";
        }
        return null;
    }
}