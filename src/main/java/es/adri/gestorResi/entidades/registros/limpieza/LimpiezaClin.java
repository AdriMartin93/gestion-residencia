package es.adri.gestorResi.entidades.registros.limpieza;

import es.adri.gestorResi.entidades.enums.EstadoTarea;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CLINICA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimpiezaClin extends LimpiezaBase {

    @Enumerated(EnumType.STRING)
    private EstadoTarea desinfecCamillas;

    @Enumerated(EnumType.STRING)
    private EstadoTarea retiradaResBio;

    @Enumerated(EnumType.STRING)
    private EstadoTarea limpiezaSuperficies;
}
