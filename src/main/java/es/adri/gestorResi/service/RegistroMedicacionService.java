package es.adri.gestorResi.service;

import es.adri.gestorResi.entidades.enums.EstadoTarea;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.registros.diariosRes.RegistroMedicacion;
import es.adri.gestorResi.entidades.salud.PautaMedica;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.PautaMedicaRepository;
import es.adri.gestorResi.repositorio.RegistroMedicacionRepository;
import es.adri.gestorResi.repositorio.ResidenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroMedicacionService {

    private final RegistroMedicacionRepository registroMedicacionRepository;
    private final PautaMedicaRepository pautaMedicaRepository;
    private final ResidenteRepository residenteRepository;
    private final EmpleadoRepository empleadoRepository;


    public List<RegistroMedicacion> mostrarTodos() {
        return registroMedicacionRepository.findAllByOrderByFechaHoraRealDesc();
    }

    public RegistroMedicacion mostrarPorId(Long id) {
        return registroMedicacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de medicación no encontrado: " + id));
    }

    public List<RegistroMedicacion> mostrarPorResidente(Long residenteId) {
        return registroMedicacionRepository.findByResidenteIdOrderByFechaHoraRealDesc(residenteId);
    }

    @Transactional
    public RegistroMedicacion registrarToma(Long pautaId, Long residenteId, Long auxiliarId, RegistroMedicacion nuevo) {
        PautaMedica pauta = pautaMedicaRepository.findById(pautaId)
                .orElseThrow(() -> new EntityNotFoundException("Pauta médica no encontrada"));

        Residente residente = residenteRepository.findById(residenteId)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));

        Empleado empleado = empleadoRepository.findById(auxiliarId)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));

        nuevo.setPautaMedica(pauta);
        nuevo.setResidente(residente);
        nuevo.setEmpleado(empleado);

        if (nuevo.getFechaHoraReal() == null) {
            nuevo.setFechaHoraReal(LocalDateTime.now());
        }

        return registroMedicacionRepository.save(nuevo);
    }

    @Transactional
    public void borrarRegistro(Long id) {
        if (!registroMedicacionRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede borrar: Registro inexistente");
        }
        registroMedicacionRepository.deleteById(id);
    }

    @Transactional
    public void actualizarEstadoTarea(Long id, EstadoTarea nuevoEstado) {
        RegistroMedicacion registro = mostrarPorId(id);
        registro.setEstadoTarea(nuevoEstado);
    }

    @Transactional
    public void editarObservaciones(Long id, String nuevasObs) {
        RegistroMedicacion registro = mostrarPorId(id);
        registro.setObservaciones(nuevasObs);
    }

    @Transactional
    public void corregirFechaHoraReal(Long id, LocalDateTime nuevaFecha) {
        RegistroMedicacion registro = mostrarPorId(id);
        registro.setFechaHoraReal(nuevaFecha);
    }
}
