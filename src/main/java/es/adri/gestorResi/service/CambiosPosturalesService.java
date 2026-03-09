package es.adri.gestorResi.service;

import es.adri.gestorResi.entidades.registros.diariosRes.CambioPostural;
import es.adri.gestorResi.repositorio.CambiosPosturalesRepository;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.ResidenteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CambiosPosturalesService {

    private final EmpleadoRepository empleadoRepository;
    private final ResidenteRepository residenteRepository;
    private final CambiosPosturalesRepository cambiosPosturalesRepository;

    public List<CambioPostural> mostrarCambios() {
        return cambiosPosturalesRepository.findAllByOrderByFechaHoraDesc();
    }

    public List<CambioPostural> mostrarPorResidente(Long residenteId) {
        return cambiosPosturalesRepository.findByResidenteIdOrderByFechaHoraDesc(residenteId);
    }

    public CambioPostural mostrarCambio(Long id){
        return cambiosPosturalesRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No se ha encontrado el registro"));
    }

    public void registrarCambio(CambioPostural cambio){
        cambiosPosturalesRepository.save(cambio);
    }

    public void borrarCambio(Long id){
        cambiosPosturalesRepository.deleteById(id);
    }


}