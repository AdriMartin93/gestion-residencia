package es.adri.gestorResi.entidades.registros.limpieza;

import es.adri.gestorResi.entidades.personas.Empleado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registros_limpieza")
@DiscriminatorColumn(name = "tipo_zona", discriminatorType = DiscriminatorType.STRING)
public abstract class LimpiezaBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    private LocalDate fecha;
    private String observaciones;
}
