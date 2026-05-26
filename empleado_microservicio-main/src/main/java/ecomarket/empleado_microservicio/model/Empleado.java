package ecomarket.empleado_microservicio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empleado;

    @Column(nullable = false)
    private String nomb_empleado;

    @Column(nullable = false)
    private String primer_apellido;

    @Column(nullable = false)
    private String segundo_apellido;

    @Column(nullable = false)
    private Cargo cargo;

    @Column(nullable = false)
    private int salario;

    @Column(nullable = false)
    private Turno turno;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefono;
}
