package ecomarket.compra_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {
    private String metodoPago; 
    private int monto;
    private boolean aceptado;
}
