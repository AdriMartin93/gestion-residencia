package es.adri.gestorResi.entidades.Dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTObase {

    private Long id;
    private String nombre;
    private String apellido;
    private String nombreUsuario;

    private Set<String> roles;
}
