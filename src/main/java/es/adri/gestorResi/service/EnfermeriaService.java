package es.adri.gestorResi.service;

import es.adri.gestorResi.entidades.enums.AccionEnfermeria;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.registros.RegistroEnfermeria;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.RegistroEnfermeriaRepository;
import es.adri.gestorResi.repositorio.ResidenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnfermeriaService {

    private final RegistroEnfermeriaRepository registroEnfermeriaRepository;
    private final ResidenteRepository residenteRepository;
    private final EmpleadoRepository empleadoRepository;

    private RegistroEnfermeria encontrarRegistro(Long id) {
        return registroEnfermeriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de enfermería no encontrado con ID: " + id));
    }

    @Transactional
    public RegistroEnfermeria crearRegistro(Long residenteId, Long enfermeroId, RegistroEnfermeria nuevoRegistro) {
        Residente residente = residenteRepository.findById(residenteId)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));

        Empleado enfermero = empleadoRepository.findById(enfermeroId)
                .orElseThrow(() -> new EntityNotFoundException("Enfermero no encontrado"));

        nuevoRegistro.setResidente(residente);
        nuevoRegistro.setEnfermero(enfermero);

        if (nuevoRegistro.getFechaHora() == null) {
            nuevoRegistro.setFechaHora(LocalDateTime.now());
        }

        return registroEnfermeriaRepository.save(nuevoRegistro);
    }

    @Transactional
    public void borrarRegistro(Long id) {
        if (!registroEnfermeriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Registro no encontrado");
        }
        registroEnfermeriaRepository.deleteById(id);
    }

    public List<RegistroEnfermeria> mostrarTodos() {
        return registroEnfermeriaRepository.findAllByOrderByFechaHoraDesc();
    }

    public RegistroEnfermeria mostrarPorId(Long id) {
        return registroEnfermeriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro no encontrado"));
    }

    public List<RegistroEnfermeria> mostrarPorResidente(Long residenteId) {

        if (!residenteRepository.existsById(residenteId)) {
            throw new EntityNotFoundException("Residente no encontrado");
        }
        return registroEnfermeriaRepository.findByResidenteIdOrderByFechaHoraDesc(residenteId);
    }



    @Transactional
    public void editarFechaHora(Long id, LocalDateTime nuevaFecha) {
        RegistroEnfermeria registro = encontrarRegistro(id);
        registro.setFechaHora(nuevaFecha);
    }

    @Transactional
    public void editarTipoAccion(Long id, AccionEnfermeria nuevaAccion) {
        RegistroEnfermeria registro = encontrarRegistro(id);
        registro.setTipoAccion(nuevaAccion);
    }

    @Transactional
    public void editarObservacion(Long id, String nuevaObservacion) {
        RegistroEnfermeria registro = encontrarRegistro(id);
        registro.setObservacion(nuevaObservacion);
    }
}
