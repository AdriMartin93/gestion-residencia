package es.adri.gestorResi.service;


import es.adri.gestorResi.entidades.enums.ActividadFisio;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.registros.RegistroFisio;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.RegistroFisioRepository;
import es.adri.gestorResi.repositorio.ResidenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FisioService {

    private final RegistroFisioRepository registroFisioRepository;
    private final ResidenteRepository residenteRepository;
    private final EmpleadoRepository  empleadoRepository;


    public List<RegistroFisio> mostrarTodos() {
        return registroFisioRepository.findAllByOrderByFechaRegistroDesc();
    }

    public RegistroFisio mostrarPorId(Long id) {
        return registroFisioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sesión de fisio no encontrada con ID: " + id));
    }

    public List<RegistroFisio> mostrarPorResidente(Long residenteId) {
        return registroFisioRepository.findByResidentesIdOrderByFechaRegistroDesc(residenteId);
    }

    @Transactional
    public RegistroFisio crearRegistro(Long empleadoId, List<Long> residentesIds, RegistroFisio nuevo) {
        Empleado fisio = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new EntityNotFoundException("Fisioterapeuta no encontrado"));

        List<Residente> participantes = residenteRepository.findAllById(residentesIds);

        nuevo.setEmpleado(fisio);
        nuevo.setResidentes(participantes);

        if (nuevo.getFechaRegistro() == null) {
            nuevo.setFechaRegistro(LocalDate.now());
        }

        return registroFisioRepository.save(nuevo);
    }

    @Transactional
    public void borrarRegistro(Long id) {
        if (!registroFisioRepository.existsById(id)) {
            throw new EntityNotFoundException("No existe el registro con ID: " + id);
        }
        registroFisioRepository.deleteById(id);
    }

    @Transactional
    public void editarActividad(Long id, ActividadFisio nuevaActividad) {
        RegistroFisio registro = mostrarPorId(id);
        registro.setActividadFisio(nuevaActividad);
    }

    @Transactional
    public void editarFecha(Long id, LocalDate nuevaFecha) {
        RegistroFisio registro = mostrarPorId(id);
        registro.setFechaRegistro(nuevaFecha);
    }

    @Transactional
    public void editarObservaciones(Long id, String nuevasObs) {
        RegistroFisio registro = mostrarPorId(id);
        registro.setObservaciones(nuevasObs);
    }

    @Transactional
    public void añadirResidenteASesion(Long sesionId, Long residenteId) {
        RegistroFisio registro = mostrarPorId(sesionId);
        Residente residente = residenteRepository.findById(residenteId)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));

        if (!registro.getResidentes().contains(residente)) {
            registro.getResidentes().add(residente);
        }
    }

    @Transactional
    public void quitarResidenteDeSesion(Long sesionId, Long residenteId) {
        RegistroFisio registro = mostrarPorId(sesionId);
        registro.getResidentes().removeIf(r -> r.getId().equals(residenteId));
    }
}
