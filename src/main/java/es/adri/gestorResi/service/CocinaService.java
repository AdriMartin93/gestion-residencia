package es.adri.gestorResi.service;

import es.adri.gestorResi.entidades.registros.ControlCocina;
import es.adri.gestorResi.repositorio.ControlCocinaRepository;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CocinaService {

    private final EmpleadoRepository empleadoRepository;
    private final ControlCocinaRepository controlCocinaRepository;

    public ControlCocina buscarControlCocina(Long id){
        return controlCocinaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("El registro no se ha encontrado"));
    }

    public List<ControlCocina> mostrarControles(){
        return controlCocinaRepository.findAllByOrderByFechaDesc();
    }

    public void agregarControlCocina(ControlCocina controlCocina){
        controlCocinaRepository.save(controlCocina);
    }

    public void borrarControlCocina(Long id){
        controlCocinaRepository.deleteById(id);
    }
}
