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
@DiscriminatorValue("ROPA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimpiezaRopa extends LimpiezaBase{

    @Enumerated(EnumType.STRING)
    private EstadoTarea lavado;

    @Enumerated(EnumType.STRING)
    private EstadoTarea secado;

    @Enumerated(EnumType.STRING)
    private EstadoTarea planchado;

    @Enumerated(EnumType.STRING)
    private EstadoTarea entrega;
}
