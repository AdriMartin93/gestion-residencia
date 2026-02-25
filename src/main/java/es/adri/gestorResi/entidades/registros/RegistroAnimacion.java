package es.adri.gestorResi.entidades.registros;


import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registros_animacion")
public class RegistroAnimacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    // Relación para saber qué residentes participaron en la actividad
    @ManyToMany
    @JoinTable(
            name = "animacion_participantes",
            joinColumns = @JoinColumn(name = "registro_id"),
            inverseJoinColumns = @JoinColumn(name = "residente_id")
    )
    private List<Residente> participantes = new ArrayList<>();

    @Column(columnDefinition = "TEXT", nullable = false)
    private String actividadRealizada;

    @Column(columnDefinition = "TEXT")
    private String observaciones;
}
