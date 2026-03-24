package es.adri.gestorResi.service;


import es.adri.gestorResi.entidades.enums.psicologia.CategoriaActividad;
import es.adri.gestorResi.entidades.enums.psicologia.TipoRegistro;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.registros.RegistroPsicologia;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.RegistroPsicologiaRepository;
import es.adri.gestorResi.repositorio.ResidenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PsicologiaService {

    private final RegistroPsicologiaRepository  registroPsicologiaRepository;
    private final ResidenteRepository residenteRepository;
    private final EmpleadoRepository empleadoRepository;

    public List<RegistroPsicologia> mostrarTodos() {
        return registroPsicologiaRepository.findAllByOrderByFechaDesc();
    }

    public RegistroPsicologia mostrarPorId(Long id) {
        return registroPsicologiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro no encontrado "));
    }

    public List<RegistroPsicologia> mostrarPorResidente(Long residenteId) {
        return registroPsicologiaRepository.findByResidentesIdOrderByFechaDesc(residenteId);
    }

    @Transactional
    public RegistroPsicologia crearRegistro(Long empleadoId, List<Long> residentesIds, RegistroPsicologia nuevo) {
        Empleado psicologo = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new EntityNotFoundException("Profesional no encontrado"));

        List<Residente> participantes = residenteRepository.findAllById(residentesIds);

        nuevo.setEmpleado(psicologo);
        nuevo.setResidentes(participantes);

        if (nuevo.getFecha() == null) {
            nuevo.setFecha(LocalDateTime.now());
        }

        return registroPsicologiaRepository.save(nuevo);
    }

    @Transactional
    public void borrarRegistro(Long id) {
        if (!registroPsicologiaRepository.existsById(id)) {
            throw new EntityNotFoundException("No existe el registro con ID: " + id);
        }
        registroPsicologiaRepository.deleteById(id);
    }

    @Transactional
    public void editarFecha(Long id, LocalDateTime nuevaFecha) {
        RegistroPsicologia registro = mostrarPorId(id);
        registro.setFecha(nuevaFecha);
    }

    @Transactional
    public void editarTipoRegistro(Long id, TipoRegistro nuevoTipo) {
        RegistroPsicologia registro = mostrarPorId(id);
        registro.setTipoRegistro(nuevoTipo);
    }

    @Transactional
    public void editarCategoria(Long id, CategoriaActividad nuevaCategoria) {
        RegistroPsicologia registro = mostrarPorId(id);
        registro.setCategoriaActividad(nuevaCategoria);
    }

    @Transactional
    public void editarDescripcion(Long id, String nuevaDesc) {
        RegistroPsicologia registro = mostrarPorId(id);
        registro.setDescripcion(nuevaDesc);
    }

    @Transactional
    public void añadirResidente(Long registroId, Long residenteId) {
        RegistroPsicologia registro = mostrarPorId(registroId);
        Residente residente = residenteRepository.findById(residenteId)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));

        if (!registro.getResidentes().contains(residente)) {
            registro.getResidentes().add(residente);
        }
    }

    @Transactional
    public void quitarResidente(Long registroId, Long residenteId) {
        RegistroPsicologia registro = mostrarPorId(registroId);
        registro.getResidentes().removeIf(r -> r.getId().equals(residenteId));
    }
}
