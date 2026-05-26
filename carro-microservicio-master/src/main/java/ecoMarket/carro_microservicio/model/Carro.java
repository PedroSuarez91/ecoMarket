package ecoMarket.carro_microservicio.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarro;

    @Column(nullable = false)
    private int total;

    @Column(nullable = false)
    private int subtotal;

    @ElementCollection
    @CollectionTable(name = "carro_productos", joinColumns = @JoinColumn(name = "id_carro"))
    @Column(name = "producto_id")
    private List<Long> listaProductos;
}
