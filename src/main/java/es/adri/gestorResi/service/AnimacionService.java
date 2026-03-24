package es.adri.gestorResi.service;

import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.registros.RegistroAnimacion;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.RegistroAnimacionRepository;
import es.adri.gestorResi.repositorio.ResidenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimacionService {

    private final RegistroAnimacionRepository registroAnimacionRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ResidenteRepository residenteRepository;


    @Transactional
    public RegistroAnimacion crearRegistro(Long empleadoId, List<Long> residentesIds, RegistroAnimacion nuevoRegistro) {

        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado con ID: " + empleadoId));

        List<Residente> participantes = residenteRepository.findAllById(residentesIds);

        nuevoRegistro.setEmpleado(empleado);
        nuevoRegistro.setParticipantes(participantes);

        if (nuevoRegistro.getFechaHora() == null) {
            nuevoRegistro.setFechaHora(LocalDateTime.now());
        }

        return registroAnimacionRepository.save(nuevoRegistro);
    }

    @Transactional
    public void borrarRegistro(Long id) {

        if (!registroAnimacionRepository.existsById(id)) {
            throw new EntityNotFoundException("Registro no encontrado.");
        }

        registroAnimacionRepository.deleteById(id);
    }

    public List<RegistroAnimacion> mostrarTodos() {
        return registroAnimacionRepository.findAllByOrderByFechaHoraDesc();
    }

    private RegistroAnimacion encontrarRegistro(Long id) {
        return registroAnimacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro no encontrado "));
    }

    @Transactional
    public void editarFechaHora(Long id, LocalDateTime nuevaFecha) {
        RegistroAnimacion registro = encontrarRegistro(id);
        registro.setFechaHora(nuevaFecha);
    }

    @Transactional
    public void editarActividad(Long id, String nuevaActividad) {
        RegistroAnimacion registro = encontrarRegistro(id);
        registro.setActividadRealizada(nuevaActividad);
    }

    @Transactional
    public void editarObservaciones(Long id, String nuevasObservaciones) {
        RegistroAnimacion registro = encontrarRegistro(id);
        registro.setObservaciones(nuevasObservaciones);
    }

    @Transactional
    public void añadirParticipante(Long registroId, Long residenteId) {
        RegistroAnimacion registro = encontrarRegistro(registroId);
        Residente residente = residenteRepository.findById(residenteId)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));

        if (!registro.getParticipantes().contains(residente)) {
            registro.getParticipantes().add(residente);
        }
    }

    @Transactional
    public void quitarParticipante(Long registroId, Long residenteId) {
        RegistroAnimacion registro = encontrarRegistro(registroId);
        Residente residente = residenteRepository.findById(residenteId)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));

        registro.getParticipantes().remove(residente);
    }

    @Transactional
    public void actualizarListaParticipantes(Long registroId, List<Long> residentesIds) {
        RegistroAnimacion registro = encontrarRegistro(registroId);
        List<Residente> nuevosParticipantes = residenteRepository.findAllById(residentesIds);
        registro.setParticipantes(nuevosParticipantes);
    }
}
