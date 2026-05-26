package ecomarket.envio_servicio_servicioso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private String fecha;
    private int total;
    private int subtotal;
    private String estado;
    private String ubicacion;
}