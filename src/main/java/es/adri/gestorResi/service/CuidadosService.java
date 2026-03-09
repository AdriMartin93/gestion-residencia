package es.adri.gestorResi.service;


import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.registros.diariosRes.CambioPostural;
import es.adri.gestorResi.entidades.registros.diariosRes.Evacuaciones;
import es.adri.gestorResi.entidades.registros.diariosRes.Higiene;
import es.adri.gestorResi.repositorio.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CuidadosService {

    private final ResidenteRepository residenteRepository;
    private final HigieneRepository higieneRepository;
    private final EvacuacionesRepository evacuacionesRepository;
    private final CambiosPosturalesRepository cambiosPosturalesRepository;
    private final EmpleadoRepository empleadoRepository;

    @Transactional
    public void registrarEvacuacion(Long residenteId, Long empleadoId, Evacuaciones registro) {

        Residente residente = residenteRepository.findByResidenteId(residenteId)
                .orElseThrow(()-> new EntityNotFoundException("Residente no encontrado"));

        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(()-> new EntityNotFoundException("Empleado no encontrado"));

        registro.setResidente(residente);
        registro.setEmpleado(empleado);

        if (registro.getFechaHora() == null) {
            registro.setFechaHora(LocalDateTime.now());
        }
        evacuacionesRepository.save(registro);
    }

    @Transactional
    public void registrarHigiene(Long residenteId, Long empleadoId, Higiene registro) {

        Residente residente = residenteRepository.findByResidenteId(residenteId)
                .orElseThrow(()-> new EntityNotFoundException("Residente no encontrado"));

        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(()-> new EntityNotFoundException("Empleado no encontrado"));

        registro.setResidente(residente);
        registro.setEmpleado(empleado);

        if(registro.getFechaHora() == null) {
            registro.setFechaHora(LocalDateTime.now());
        }
        higieneRepository.save(registro);
    }

    @Transactional
    public void registrarCambioPostural(Long residenteId, Long empleadoId, CambioPostural registro) {

        Residente residente = residenteRepository.findByResidenteId(residenteId)
                .orElseThrow(()-> new EntityNotFoundException("Residente no encontrado"));

        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(()-> new EntityNotFoundException("Empleado no encontrado"));

        registro.setResidente(residente);
        registro.setEmpleado(empleado);

        if(registro.getFechaHora() == null) {
            registro.setFechaHora(LocalDateTime.now());
        }
        cambiosPosturalesRepository.save(registro);
    }

    @Transactional

    public void borrarEvacuacion(Long id){
        if(!evacuacionesRepository.existsById(id)){
            throw new  EntityNotFoundException("No se udo eliminar: el registro no existe");
        }
        evacuacionesRepository.deleteById(id);
    }

    @Transactional
    public void borrarHigiene(Long id){
        if(!higieneRepository.existsById(id)){
            throw new EntityNotFoundException("No se udo eliminar: el registro no existe");
        }
        higieneRepository.deleteById(id);
    }


    @Transactional
    public void borrarCambioPostural(Long id){
        if(!cambiosPosturalesRepository.existsById(id)){
            throw new EntityNotFoundException("No se udo eliminar: el registro no existe");
        }
        cambiosPosturalesRepository.deleteById(id);
    }


    public List<Evacuaciones> listarEvacuaciones(){
        return evacuacionesRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaHora"));
    }

    public List<Evacuaciones> listarEvacuacionesPorFecha(LocalDate fecha){
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);

        return evacuacionesRepository.findByFechaHoraBetweenOrderByFechaHoraDesc(inicio, fin);
    }

    public List<Evacuaciones> listarEvacuacionesPorResidente(Long residenteId) {

        return evacuacionesRepository.findByResidenteIdOrderByFechaHoraDesc(residenteId);
    }

    public List<Evacuaciones> listarEvacuacionesResidentePorDia(Long residenteId, LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);
        return evacuacionesRepository.findByResidenteIdAndFechaHoraBetweenOrderByFechaHoraDesc(residenteId, inicio, fin);
    }


    public List<CambioPostural> listarCambios(){
        return cambiosPosturalesRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaHora"));
    }

    public List<CambioPostural> listarCambioPorFecha(LocalDate fecha){
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);

        return cambiosPosturalesRepository.findByFechaHoraBetweenOrderByFechaHoraDesc(inicio, fin);
    }

    public List<CambioPostural> listarCambioPorResidente(Long residenteId) {

        return cambiosPosturalesRepository.findByResidenteIdOrderByFechaHoraDesc(residenteId);
    }

    public List<CambioPostural> listarCambioResidentePorDia(Long residenteId, LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);

        return cambiosPosturalesRepository.findByResidenteIdAndFechaHoraBetweenOrderByFechaHoraDesc(residenteId, inicio, fin);
    }


    public List<Higiene> listarHigiene(){
        return higieneRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaHora"));
    }

    public List<Higiene> listarHigienePorFecha(LocalDate fecha){
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);

        return higieneRepository.findByFechaHoraBetweenOrderByFechaHoraDesc(inicio, fin);
    }

    public List<Higiene> listarHigienePorResidente(Long residenteId) {

        return higieneRepository.findByResidenteIdOrderByFechaHoraDesc(residenteId);
    }

    public List<Higiene> listarHigieneResidentePorDia(Long residenteId, LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);

        return higieneRepository.findByResidenteIdAndFechaHoraBetweenOrderByFechaHoraDesc(residenteId, inicio, fin);
    }

}
