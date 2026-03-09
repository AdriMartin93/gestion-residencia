package es.adri.gestorResi.entidades.registros.diariosRes;


import es.adri.gestorResi.entidades.enums.evacuaciones.CantidadDepo;
import es.adri.gestorResi.entidades.enums.evacuaciones.CantidadOrina;
import es.adri.gestorResi.entidades.enums.evacuaciones.TipoDepo;
import es.adri.gestorResi.entidades.enums.evacuaciones.TipoOrina;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.personas.Empleado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registros_evacuaciones")
public class Evacuaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Residente residente;

    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    private TipoOrina orina;

    @Enumerated(EnumType.STRING)
    private CantidadOrina cantOrina;

    @Enumerated(EnumType.STRING)
    private CantidadDepo cantDepo;

    @Enumerated(EnumType.STRING)
    private TipoDepo depo;

    @ManyToOne
    private Empleado empleado;
}
