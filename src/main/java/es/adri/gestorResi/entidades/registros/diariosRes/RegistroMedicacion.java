package es.adri.gestorResi.entidades.registros.diariosRes;


import es.adri.gestorResi.entidades.enums.EstadoTarea;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.salud.PautaMedica;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RegistroMedicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PautaMedica pautaMedica;

    @ManyToOne(fetch = FetchType.LAZY)
    private Residente residente;

    private LocalDateTime fechaHoraReal;

    @Enumerated(EnumType.STRING)
    private EstadoTarea estadoTarea;

    @ManyToOne
    private Empleado auxiliar;

    private String observaciones;
}
