package ecomarket.pago_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoletaDTO {
    private String tipo; 
    private String fechaEmision;
    private int subTotal;
    private int total;
    private List<Long> contenido;
}