package ecomarket.envio_servicio_servicioso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {
    private Long idPago;
    private String metodoPago;
    private int monto;
    private boolean aceptado; // campo que verificamos antes de crear el envío
}