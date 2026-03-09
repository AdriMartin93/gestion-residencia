package es.adri.gestorResi.service;


import es.adri.gestorResi.entidades.registros.Incidencia;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.IncidenciaRepository;
import es.adri.gestorResi.repositorio.ResidenteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidenciasService {

    private final EmpleadoRepository empleadoRepository;
    private final ResidenteRepository residenteRepository;
    private final IncidenciaRepository incidenciaRepository;

    public void registrarIncidencia(Incidencia incidencia){
        incidenciaRepository.save(incidencia);
    }

    public List<Incidencia> mostrarIncidencias(){
        return incidenciaRepository.findAllByOrderByFechaHoraDesc(LocalDateTime.now());
    }

    public List<Incidencia> mostrarIncidenciasByResidenteId(Long id){
        return incidenciaRepository.findByResidenteIdOrderByFechaHoraDesc(id);
    }

    public Incidencia mostrarIncidenciaById(Long id){
        return incidenciaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Incidencia no encontrada"));
    }

    public void borrarIncidencia(Long id){
        incidenciaRepository.deleteById(id);
    }
}



