package es.adri.gestorResi.entidades.salud;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HistorialMedico {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String grupoSanguineo;

    @Column(columnDefinition = "TEXT")
    private String antecedentesClinicos;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> alergias;

    private String dieta;
    private String movilidad;

    @OneToMany(mappedBy = "historialMedico",cascade = CascadeType.ALL)
    private List<Tratamiento> tratamientos;
}
