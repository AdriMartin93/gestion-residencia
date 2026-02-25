package es.adri.gestorResi.entidades.registros.diariosRes;


import es.adri.gestorResi.entidades.enums.EstadoTarea;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registros_higiene")
public class Higiene {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residente_id")
    private Residente residente;

    @Enumerated(EnumType.STRING)
    private EstadoTarea higieneIntima;

    @Enumerated(EnumType.STRING)
    private EstadoTarea ducha;

    @Enumerated(EnumType.STRING)
    private EstadoTarea corteUnas;

    @Enumerated(EnumType.STRING)
    private EstadoTarea higieneBucal;

    @Enumerated(EnumType.STRING)
    private EstadoTarea afeitado;

    @Enumerated(EnumType.STRING)
    private EstadoTarea hidratacionPiel;

    @Enumerated(EnumType.STRING)
    private EstadoTarea levantarResidente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}
