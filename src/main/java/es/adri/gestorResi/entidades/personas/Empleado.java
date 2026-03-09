package es.adri.gestorResi.entidades.personas;

import es.adri.gestorResi.entidades.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Empleado {

    private Long id;
    private String nombreUsuario;
    private String dni;
    private String nombre;
    private String apellidos;
    private String password;
    private String email;
    private String telefono;

    @ElementCollection(targetClass = Roles.class)
    @CollectionTable(name = "empleado_roles", joinColumns = @JoinColumn(name = "empleado_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;

}

