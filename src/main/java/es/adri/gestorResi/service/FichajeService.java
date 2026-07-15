package es.adri.gestorResi.service;

import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.registros.Fichaje;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.FichajeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FichajeService {

    private final FichajeRepository fichajeRepository;
    private final EmpleadoRepository empleadoRepository;

    @Transactional
    public String alternarFichaje(String username) {
        Empleado empleado = empleadoRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con el usuario: " + username));


        Optional<Fichaje> ultimoFichajeOpt = fichajeRepository.findFirstByEmpleadoNombreUsuarioOrderByEntradaDesc(username);


        if (ultimoFichajeOpt.isPresent() && ultimoFichajeOpt.get().getSalida() == null) {
            Fichaje fichajeAbierto = ultimoFichajeOpt.get();
            fichajeAbierto.setSalida(LocalDateTime.now());
            fichajeRepository.save(fichajeAbierto);
            return "SALIDA_REGISTRADA";
        }


        Fichaje nuevoFichaje = new Fichaje();
        nuevoFichaje.setEmpleado(empleado);
        nuevoFichaje.setEntrada(LocalDateTime.now());
        fichajeRepository.save(nuevoFichaje);
        return "ENTRADA_REGISTRADA";
    }

    public boolean estaTrabajandoActualmente(String username) {
        Optional<Fichaje> ultimo = fichajeRepository.findFirstByEmpleadoNombreUsuarioOrderByEntradaDesc(username);
        return ultimo.isPresent() && ultimo.get().getSalida() == null;
    }

    public List<Fichaje> obtenerTodosLosFichajes() {
        return fichajeRepository.findAllByOrderByEntradaDesc();
    }
}