package es.adri.gestorResi.entidades.registros;

import es.adri.gestorResi.entidades.enums.psicologia.CategoriaActividad;
import es.adri.gestorResi.entidades.enums.psicologia.TipoRegistro;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registros_psicologia")
public class RegistroPsicologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado empleado;

    @Enumerated(EnumType.STRING)
    private TipoRegistro tipoRegistro;

    @ManyToMany
    @JoinTable(
            name = "psicologia_participantes",
            joinColumns = @JoinColumn(name = "registro_id"),
            inverseJoinColumns = @JoinColumn(name = "residente_id")
    )
    private List<Residente> residentes;

    @Enumerated(EnumType.STRING)
    private CategoriaActividad categoriaActividad;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

}
