package ecomarket.producto_servicio.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int precio;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Column(nullable = false)
    private int stock_tienda;

    @Column(nullable = false)
    private int stock_catalogo;

    @Column(nullable = false)
    private int stock_inventario;

    @ElementCollection
    @CollectionTable(
        name = "producto_descripciones",
        joinColumns = @JoinColumn(name = "producto_id")
    )
    @Column(name = "descripcion")
    private List<String> description;
}