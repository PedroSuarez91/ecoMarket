package ecomarket.pago_servicio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @Column(nullable = false)
    private int monto;

    @Column(nullable = false)
    private boolean aceptado;
}