package es.adri.gestorResi.entidades;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    private String id;
    private String nombreUsuario;
    private String dni;
    private String nombre;
    private String apellidos;
    private String password;
    private String email;
    private String telefono;
    private Set<Rol> roles;

}

