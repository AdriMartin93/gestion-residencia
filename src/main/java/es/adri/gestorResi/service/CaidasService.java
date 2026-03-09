package es.adri.gestorResi.service;


import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.registros.Caidas;
import es.adri.gestorResi.repositorio.CaidasRepository;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.HigieneRepository;
import es.adri.gestorResi.repositorio.ResidenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CaidasService {

    private final EmpleadoRepository empleadoRepository;
    private final ResidenteRepository residenteRepository;
    private final CaidasRepository caidasRepository;


    @Transactional
    public void registrarCaida(Long empleadoId, Long residenteId, Caidas caidas){

        Residente residente = residenteRepository.findByResidenteId(residenteId)
                .orElseThrow(()-> new EntityNotFoundException("Residente no encontrado"));

        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(()-> new EntityNotFoundException("Empleado no encontrado"));


        caidas.setResidente(residente);
        caidas.setEmpleado(empleado);

        if(caidas.getFechaHora() == null){
            caidas.setFechaHora(LocalDateTime.now());
        }
        caidasRepository.save(caidas);
    }

    public List<Caidas> mostrarCaidas(){
        return caidasRepository.findAllByOrderByFechaHoraDesc();
    }

    public Caidas mostrarCaidaPorId(Long id){

        return caidasRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Caida no encontrada"));
    }

    public void borrarCaida(Long id){
        caidasRepository.deleteById(id);
    }
}
