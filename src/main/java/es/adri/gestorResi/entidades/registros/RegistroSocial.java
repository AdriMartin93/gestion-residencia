package es.adri.gestorResi.entidades.registros;


import es.adri.gestorResi.entidades.enums.trabajoSocial.CategoriaSocial;
import es.adri.gestorResi.entidades.enums.trabajoSocial.EstadoTramite;
import es.adri.gestorResi.entidades.enums.trabajoSocial.TipoIntervFamiliar;
import es.adri.gestorResi.entidades.enums.trabajoSocial.TipoRecurso;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registros_sociales")
public class RegistroSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residente_id")
    private Residente residente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id")
    private Empleado trabajadorSocial;

    @Enumerated(EnumType.STRING)
    private CategoriaSocial categoria;


    @Enumerated(EnumType.STRING)
    private TipoRecurso recursoDetalle;

    @Enumerated(EnumType.STRING)
    private TipoIntervFamiliar intervencionDetalle;

    @Enumerated(EnumType.STRING)
    private EstadoTramite estado;

    @Column(columnDefinition = "TEXT")
    private String gestionesRealizadas;

    private String numeroExpediente;
    private LocalDate fechapresentacion;
    private LocalDate fechaVencimiento;

    private boolean alertaSocial;
}
