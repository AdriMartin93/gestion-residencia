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
    public Residente editarResidente(Long id, Residente datosNuevos) {

        Residente residenteExistente = residenteRepository.findByResidenteId(id)
                .orElseThrow(() -> new EntityNotFoundException("No se puede editar: Residente no encontrado"));

        residenteExistente.setNombre(datosNuevos.getNombre());
        residenteExistente.setApellidos(datosNuevos.getApellidos());
        residenteExistente.setDni(datosNuevos.getDni());
        residenteExistente.setTis(datosNuevos.getTis());
        residenteExistente.setFecha_nacimiento(datosNuevos.getFecha_nacimiento());
        residenteExistente.setHabitacion(datosNuevos.getHabitacion());

        return residenteRepository.save(residenteExistente);
    }
}

