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
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResidenteService {

    private final ResidenteRepository residenteRepository;

    @Transactional
    public Residente registrarResidente(Residente residente){
        if(residente.getHistorialMedico() == null) {
            residente.setHistorialMedico(new HistorialMedico());
        }
        if (residente.getContactos() != null) {
            java.util.Set<Contacto> contactosIntermedios = new java.util.HashSet<>(residente.getContactos());
            residente.getContactos().clear();
            residente.getContactos().addAll(contactosIntermedios);
        }
        return residenteRepository.save(residente);
    }

    @Transactional
    public void borrarResidente(Residente residente){

        residenteRepository.softDeleteById(residente.getId());
    }

    public List<Residente> listarTodos() {
        return residenteRepository.findAllByActivoTrue();
    }

    public Residente buscarPorId(Long id) {
        return residenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado con ID: " + id));
    }


    @Transactional
    public void agregarContacto(Long id, Contacto contacto){
        Residente residente = residenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));
        residente.getContactos().add(contacto);
        residenteRepository.save(residente);
    }

    @Transactional
    public void borrarContacto(Long residenteId, Contacto contacto){
        Residente residente = residenteRepository.findById(residenteId)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));
        residente.getContactos().remove(contacto);
    }

    @Transactional
    public void actualizarParcial(Long id, Map<String, Object> campos) {
        Residente residente = residenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Residente no encontrado"));

        campos.forEach((clave, valor) -> {
            switch (clave) {
                case "nombre":
                    residente.setNombre((String) valor);
                    break;
                case "apellidos":
                    residente.setApellidos((String) valor);
                    break;
                case "dni":
                    residente.setDni((String) valor);
                    break;
                case "tis":
                    residente.setTis((String) valor);
                    break;
                case "habitacion":
                    residente.setHabitacion((String) valor);
                    break;
                case "fechaNacimiento":
                    residente.setFechaNacimiento(LocalDate.parse((String) valor));
                    break;
                case "contactos":
                    if (valor instanceof List) {
                        List<Map<String, Object>> listaContactosMap = (List<Map<String, Object>>) valor;

                        for (Map<String, Object> conMap : listaContactosMap) {
                            Contacto nuevoContacto = new Contacto();
                            nuevoContacto.setNombre((String) conMap.get("nombre"));
                            nuevoContacto.setParentesco((String) conMap.get("parentesco"));
                            nuevoContacto.setTelefono((String) conMap.get("telefono"));
                            nuevoContacto.setEmail((String) conMap.get("email"));
                            residente.getContactos().add(nuevoContacto);
                        }
                    }
                    break;
                default:
                    throw new IllegalArgumentException("El campo " + clave + " no es editable o no existe");
            }
        });

        residenteRepository.save(residente);
    }


}

