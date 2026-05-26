package ecomarket.pedido_servicio_servicioso.model;

import java.util.List;

import lombok.Data;

@Data
public class CarroDTO {

    private Long idCarro;
    private int total;
    private int subtotal;
    private List<Long> listaProductos;
}
