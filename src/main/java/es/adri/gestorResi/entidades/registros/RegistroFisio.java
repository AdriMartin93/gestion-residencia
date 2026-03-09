package es.adri.gestorResi.entidades.registros;


import es.adri.gestorResi.entidades.enums.ActividadFisio;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RegistroFisio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado empleado;

    @Enumerated(EnumType.STRING)
    private ActividadFisio actividadFisio;

    private LocalDate fechaRegistro;

    @ManyToMany
    @JoinTable(name = "participacion_sesion_fisio",
            joinColumns = @JoinColumn(name = "sesion_id"),
            inverseJoinColumns = @JoinColumn(name = "residente_id"))
    private List<Residente> residentes;

    private String observaciones;
}
