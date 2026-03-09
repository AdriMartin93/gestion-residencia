package es.adri.gestorResi.entidades.personas;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Contacto {

    private String nombre;
    private String parentesco;
    private String telefono;
    private String email;

}
