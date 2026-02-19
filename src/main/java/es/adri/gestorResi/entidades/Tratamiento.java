package es.adri.gestorResi.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreMedicamento;
    private String dosis;
    private String frecuencia;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String viaAdministracion;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "historial_medico_id")
    private HistorialMedico historialMedico;
}
