package es.adri.gestorResi.entidades.registros.limpieza;

import es.adri.gestorResi.entidades.enums.EstadoTarea;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("HABITACION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimpiezaHab extends LimpiezaBase {

    @Enumerated(EnumType.STRING)
    private EstadoTarea cambioSabanas;

    @Enumerated(EnumType.STRING)
    private EstadoTarea limpiezaSuperficies;

    @Enumerated(EnumType.STRING)
    private EstadoTarea limpiezaLavabo;

    @Enumerated(EnumType.STRING)
    private EstadoTarea reposicion;
}
