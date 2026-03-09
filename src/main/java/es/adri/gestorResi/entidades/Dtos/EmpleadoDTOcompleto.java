package es.adri.gestorResi.entidades.Dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTOcompleto {

    private Long id;
    private String nombreUsuario;
    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;

    private Set<String> roles;
}
