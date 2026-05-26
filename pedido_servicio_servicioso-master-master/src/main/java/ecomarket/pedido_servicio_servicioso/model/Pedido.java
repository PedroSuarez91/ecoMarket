package ecomarket.pedido_servicio_servicioso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fecha;

    @Column(nullable = false)
    private int total;

    @Column(nullable = false)
    private int subtotal;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(nullable = false)
    private String ubicacion;
}