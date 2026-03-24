package es.adri.gestorResi.service;


import es.adri.gestorResi.entidades.enums.trabajoSocial.CategoriaSocial;
import es.adri.gestorResi.entidades.enums.trabajoSocial.EstadoTramite;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.registros.RegistroSocial;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.RegistroSocialRepository;
import es.adri.gestorResi.repositorio.ResidenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialService {

    private final RegistroSocialRepository registroSocialRepository;
    private final ResidenteRepository residenteRepository;
    private final EmpleadoRepository empleadoRepository;


    @Transactional
    public RegistroSocial crearRegistro(Long residenteId, Long empleadoId, RegistroSocial nuevo) {
        Residente residente = residenteRepository.findById(residenteId)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));

        Empleado trabajadorSocial = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador Social no encontrado"));

        nuevo.setResidente(residente);
        nuevo.setTrabajadorSocial(trabajadorSocial);

        if (nuevo.getFechaRegistro() == null) {
            nuevo.setFechaRegistro(LocalDateTime.now());
        }

        return registroSocialRepository.save(nuevo);
    }

    @Transactional
    public void borrarRegistro(Long id) {
        if (!registroSocialRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede borrar: Registro inexistente");
        }
        registroSocialRepository.deleteById(id);
    }

    @Transactional
    public void actualizarEstado(Long id, EstadoTramite nuevoEstado) {
        RegistroSocial registro = mostrarPorId(id);
        registro.setEstado(nuevoEstado);
    }

    @Transactional
    public void editarCategoria(Long id, CategoriaSocial nuevaCategoria) {
        RegistroSocial registro = mostrarPorId(id);
        registro.setCategoria(nuevaCategoria);
    }

    @Transactional
    public void editarGestiones(Long id, String nuevasGestiones) {
        RegistroSocial registro = mostrarPorId(id);
        registro.setGestionesRealizadas(nuevasGestiones);
    }

    @Transactional
    public void actualizarFechasTramite(Long id, LocalDate presentacion, LocalDate vencimiento) {
        RegistroSocial registro = mostrarPorId(id);
        registro.setFechapresentacion(presentacion);
        registro.setFechaVencimiento(vencimiento);
    }

    @Transactional
    public void conmutarAlertaSocial(Long id, boolean estadoAlerta) {
        RegistroSocial registro = mostrarPorId(id);
        registro.setAlertaSocial(estadoAlerta);
    }

    @Transactional
    public void editarNumeroExpediente(Long id, String numExpediente) {
        RegistroSocial registro = mostrarPorId(id);
        registro.setNumeroExpediente(numExpediente);
    }

    public List<RegistroSocial> mostrarTodos() {
        return registroSocialRepository.findAllByOrderByFechaRegistroDesc();
    }

    public RegistroSocial mostrarPorId(Long id) {
        return registroSocialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro social no encontrado: " + id));
    }

    public List<RegistroSocial> mostrarPorResidente(Long residenteId) {
        return registroSocialRepository.findByResidenteIdOrderByFechaRegistroDesc(residenteId);
    }

}
