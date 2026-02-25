package es.adri.gestorResi.entidades.registros.diariosRes;


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

    private LocalDateTime fechaHora; // Fecha y hora del cambio

    private String posicion; // Ej: Izquierda, Derecha, Espalda, Sentado

    private String observaciones;

}

