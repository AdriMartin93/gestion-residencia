package es.adri.gestorResi.service;


import es.adri.gestorResi.entidades.registros.Incidencia;
import es.adri.gestorResi.entidades.registros.ParteDiario;
import es.adri.gestorResi.repositorio.ParteDiarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParteService {

    private final ParteDiarioRepository parteDiarioRepository;

    @Transactional
    public void registrarParte(ParteDiario parte){

        parteDiarioRepository.save(parte);
    }

    public List<ParteDiario> mostrarPartes(){
        return parteDiarioRepository.findAllByOrderByFechaDesc();
    }

    public List<ParteDiario> mostrarParteByEmpleadoId(Long id){
        return parteDiarioRepository.findByEmpleadoId(id);
    }

    public ParteDiario mostrarParteById(Long id){
        return parteDiarioRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Incidencia no encontrada"));
    }

    @Transactional
    public void actualizarContenido(Long id, String contenidoCorregido) {

        ParteDiario parte = parteDiarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el parte con ID: " + id));

        parte.setContenido(contenidoCorregido);
    }

    @Transactional
    public void borrarParte(Long id){

        parteDiarioRepository.deleteById(id);
    }

}
