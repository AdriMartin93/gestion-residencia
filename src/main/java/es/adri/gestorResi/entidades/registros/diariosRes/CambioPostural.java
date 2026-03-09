package es.adri.gestorResi.entidades.registros.diariosRes;


import es.adri.gestorResi.entidades.enums.Posiciones;
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
public class CambioPostural {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "residente_id")
    private Residente residente;
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    private Posiciones posicion;

    private String observaciones;

}

