package ecoMarket.tienda_microservicio.model;

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
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tiendas")
public class Tienda {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String nombre;

        @Column(nullable = false)
        private String ubicacion;

        @ElementCollection
        @CollectionTable(name = "tienda_normas", joinColumns = @JoinColumn(name = "tienda_id"))
        @Column(name = "norma", nullable = false)
        private List<String> normas = new ArrayList<>();

        @Column(nullable = false)
        private String horarioTienda;

        @ElementCollection
        @CollectionTable(name = "tienda_empleados", joinColumns = @JoinColumn(name = "tienda_id"))
        @Column(name = "empleado_id")
        private List<Long> listaEmpleados = new ArrayList<>();
}
