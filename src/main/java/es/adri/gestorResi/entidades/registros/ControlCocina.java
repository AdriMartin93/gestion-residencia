package es.adri.gestorResi.entidades.registros;

import es.adri.gestorResi.entidades.enums.EstadoTarea;
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
public class ControlCocina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado empleado;

    @Enumerated(EnumType.STRING)
    private EstadoTarea superficiesLimpias;

    @Enumerated(EnumType.STRING)
    private EstadoTarea uniformeCorrecto;

    @Enumerated(EnumType.STRING)
    private EstadoTarea temperaturaCamaras;

    @Enumerated(EnumType.STRING)
    private EstadoTarea etiquetado;

    @Enumerated(EnumType.STRING)
    private EstadoTarea muestrasTestigo;

    @Enumerated(EnumType.STRING)
    private EstadoTarea basuraRetirada;

    @Enumerated(EnumType.STRING)
    private EstadoTarea lavadoPlatos;

    @Enumerated(EnumType.STRING)
    private EstadoTarea limpiezaCocina;

    private String observaciones;
}
