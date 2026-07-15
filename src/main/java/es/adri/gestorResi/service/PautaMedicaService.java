package es.adri.gestorResi.service;


import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.entidades.salud.PautaMedica;
import es.adri.gestorResi.repositorio.PautaMedicaRepository;
import es.adri.gestorResi.repositorio.ResidenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PautaMedicaService {

    private final PautaMedicaRepository pautaMedicaRepository;
    private final ResidenteRepository residenteRepository;

    @Transactional
    public PautaMedica crearPauta(Long residenteId, PautaMedica nuevaPauta){
        Residente residente = residenteRepository.findById(residenteId)
                .orElseThrow(()-> new EntityNotFoundException("Residente no encontrado"));
        nuevaPauta.setResidente(residente);
        nuevaPauta.setHistorialMedico(residente.getHistorialMedico());

        return pautaMedicaRepository.save(nuevaPauta);
    }

    @Transactional
    public void borrarPauta(Long id){

        pautaMedicaRepository.deleteById(id);
    }

    public PautaMedica encontrarPauta(Long id) {

        return pautaMedicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pauta Médica no encontrada"));
    }

    public List<PautaMedica> obtenerPautasPorResidente(Long residenteId) {
        return pautaMedicaRepository.findByResidenteId(residenteId);
    }

    @Transactional
    public void editarMedicamento(Long id, String nuevoMedicamento) {
        PautaMedica pauta = encontrarPauta(id);
        pauta.setMedicamento(nuevoMedicamento);
    }

    @Transactional
    public void editarDosis(Long id, String nuevaDosis) {
        PautaMedica pauta = encontrarPauta(id);
        pauta.setDosis(nuevaDosis);
    }

    @Transactional
    public void editarFechaHora(Long id, LocalDateTime nuevaFecha) {
        PautaMedica pauta = encontrarPauta(id);
        pauta.setFechaHora(nuevaFecha);
    }

    @Transactional
    public void editarDuracion(Long id, String nuevaDuracion) {
        PautaMedica pauta = encontrarPauta(id);
        pauta.setDuracion(nuevaDuracion);
    }

    @Transactional
    public void editarObservaciones(Long id, String nuevasObservaciones) {
        PautaMedica pauta = encontrarPauta(id);
        pauta.setObservaciones(nuevasObservaciones);
    }


}
