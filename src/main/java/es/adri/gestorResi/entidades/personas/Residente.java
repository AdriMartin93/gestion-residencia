package es.adri.gestorResi.entidades.personas;

import es.adri.gestorResi.entidades.salud.HistorialMedico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Residente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String tis;
    private String nombre;
    private String apellidos;
    private LocalDate fecha_nacimiento;
    private String habitacion;

    @ElementCollection
    @CollectionTable(name = "residente_contactos", joinColumns = @JoinColumn(name = "residente_id"))
    private Set<Contacto> contactos = new HashSet();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "historial_medico_id")
    private HistorialMedico historialMedico;

}
