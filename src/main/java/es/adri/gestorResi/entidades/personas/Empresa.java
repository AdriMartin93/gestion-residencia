package es.adri.gestorResi.entidades.personas;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El CIF es obligatorio")
    @Pattern(regexp = "^[A-HJNP-SU-W][0-9]{8}$", message = "El formato del CIF no es válido en España")
    @Column(unique = true, nullable = false)
    private String cif;

    @NotBlank(message = "El nombre comercial es obligatorio")
    @Column(unique = true, nullable = false)
    private String nombreComercial;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es correcto")
    @Column(unique = true, nullable = false)
    private String email;
}