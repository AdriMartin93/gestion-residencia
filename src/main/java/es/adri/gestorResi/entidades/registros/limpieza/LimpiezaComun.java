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
@DiscriminatorValue("COMUNES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimpiezaComun extends LimpiezaBase{

    @Enumerated(EnumType.STRING)
    private EstadoTarea limpiezaAscensores;

    @Enumerated(EnumType.STRING)
    private EstadoTarea limpiezaSillas;

    @Enumerated(EnumType.STRING)
    private EstadoTarea limpiezaSuperficies;

}
