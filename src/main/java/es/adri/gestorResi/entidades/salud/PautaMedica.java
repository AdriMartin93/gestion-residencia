package es.adri.gestorResi.entidades.salud;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.adri.gestorResi.entidades.personas.Residente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PautaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties({"contactos", "historialMedico"})
    private Residente residente;

    @ManyToOne
    @JoinColumn(name = "historial_medico_id")
    @JsonIgnoreProperties("pautaMedica")
    private HistorialMedico historialMedico;

    private String medicamento;
    private String dosis;
    private LocalDateTime fechaHora;
    private String duracion;
    private String observaciones;
}
