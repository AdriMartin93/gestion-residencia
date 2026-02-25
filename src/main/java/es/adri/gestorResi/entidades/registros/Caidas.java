package es.adri.gestorResi.entidades.registros;

import es.adri.gestorResi.entidades.enums.Caidas.Calzado;
import es.adri.gestorResi.entidades.enums.Caidas.ConsecuenciaCaida;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.personas.Empleado;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "registros_caidas")
public class Caidas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "residente_id")
    private Residente residente;

    private LocalDateTime fechaHora;

    private String lugar;

    private String actividad;

    private String descripcionCaida;

    @Enumerated(EnumType.STRING)
    private Calzado calzado;

    boolean consciente;

    @ElementCollection(targetClass = ConsecuenciaCaida.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "caida_consecuencias", joinColumns = @JoinColumn(name = "caida_id"))
    @Column(name = "consecuencia")
    private Set<ConsecuenciaCaida> consecuencias = new HashSet<>();

    private String acciones;

    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado auxiliar;

}
