package es.adri.gestorResi.entidades.registros;


import es.adri.gestorResi.entidades.enums.Incidencias;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Residente residente;

    private LocalDateTime fechaHora;

    private Incidencias tipo;

    @Column(length = 1000)
    private String descripcion;

    @ManyToOne
    private Empleado empleado;
}
