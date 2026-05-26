package ecomarket.compra_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuponDTO {
    private Long id;
    private int monto;
    private String tipo; 
}
