package es.adri.gestorResi.service;


import es.adri.gestorResi.entidades.personas.Contacto;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.salud.HistorialMedico;
import es.adri.gestorResi.entidades.salud.PautaMedica;
import es.adri.gestorResi.repositorio.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ResidenteService {

    private final ResidenteRepository residenteRepository;

    @Transactional
    public Residente registrarResidente(Residente residente){
        if(residente.getHistorialMedico() == null) {
            residente.setHistorialMedico(new HistorialMedico());
        }
        return residenteRepository.save(residente);
    }

    @Transactional
    public void borrarResidente(Residente residente){

        residenteRepository.delete(residente);
    }

    @Transactional
    public void agregarContacto(Long id, Contacto contacto){
        Residente residente = residenteRepository.findByResidenteId(id)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));
        residente.getContactos().add(contacto);
        residenteRepository.save(residente);
    }

    @Transactional
    public void borrarContacto(Long residenteId, Contacto contacto){
        Residente residente = residenteRepository.findByResidenteId(residenteId)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));
        residente.getContactos().remove(contacto);
    }

    @Transactional
    public void editarNombre(Long id, String nuevoNombre) {
        Residente residente = residenteRepository.findByResidenteId(id)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));
        residente.setNombre(nuevoNombre);
    }

    @Transactional
    public void editarApellidos(Long id, String nuevosApellidos) {
        Residente residente = residenteRepository.findByResidenteId(id)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));
        residente.setApellidos(nuevosApellidos);
    }

    @Transactional
    public void editarDni(Long id, String nuevoDni) {
        Residente residente = residenteRepository.findByResidenteId(id)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));
        residente.setDni(nuevoDni);
    }

    @Transactional
    public void editarTis(Long id, String nuevoTis) {
        Residente residente = residenteRepository.findByResidenteId(id)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));
        residente.setTis(nuevoTis);
    }

    @Transactional
    public void editarFechaNacimiento(Long id, LocalDate nuevaFecha) {
        Residente residente = residenteRepository.findByResidenteId(id)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));
        residente.setFechaNacimiento(nuevaFecha);
    }

    @Transactional
    public void editarHabitacion(Long id, String nuevaHabitacion) {
        Residente residente = residenteRepository.findByResidenteId(id)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));
        residente.setHabitacion(nuevaHabitacion);
    }
}

