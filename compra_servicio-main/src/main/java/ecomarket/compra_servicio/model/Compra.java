package ecomarket.compra_servicio.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fecha;

    @ElementCollection
    @CollectionTable(
        name = "compra_productos", 
        joinColumns = @JoinColumn(name = "compra_id")
    )
    @Column(name = "producto_id")
    private List<Long> contenido; 

    @Column(nullable = false)
    private int subtotal;

    @Column(nullable = false)
    private int total;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;
}