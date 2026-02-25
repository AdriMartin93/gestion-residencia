package es.adri.gestorResi.entidades.registros;


import es.adri.gestorResi.entidades.enums.AccionEnfermeria;
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
@Table(name = "registros_enfermeria")
public class RegistroEnfermeria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residente_id", nullable = false)
    private Residente residente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enfermero_id", nullable = false)
    private Empleado enfermero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccionEnfermeria tipoAccion;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String observacion;
}
