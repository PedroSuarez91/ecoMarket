package ecomarket.boleta_servicio.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "boletas_facturas")
public class BoletaFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGeneral;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipo;

    @Column(nullable = false)
    private String fechaEmision;

    @Column(nullable = false)
    private int subTotal;

    @Column(nullable = false)
    private int total;

    @ElementCollection
    @CollectionTable(
        name = "boleta_contenido", 
        joinColumns = @JoinColumn(name = "boleta_id")
    )
    @Column(name = "producto_id")
    private List<Long> contenido;
}