package es.adri.gestorResi.entidades.Dtos;

import es.adri.gestorResi.entidades.personas.Contacto;
import es.adri.gestorResi.entidades.salud.HistorialMedico;

import java.util.Set;

public class ResidenteDTOcompleto {
    private Long id;
    private String dni;
    private String tis;
    private String nombre;
    private String apellidos;
    private String habitacion;

    private Set<Contacto> contactos;
    private HistorialMedico historialMedico;
}

