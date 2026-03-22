package es.adri.gestorResi.service;


import es.adri.gestorResi.entidades.salud.HistorialMedico;
import es.adri.gestorResi.repositorio.HistorialMedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialService {

    private final HistorialMedicoRepository historialMedicoRepository;

    @Transactional
    public HistorialMedico obtenerPorResidente(Long residenteId) {
        return historialMedicoRepository.findByResidenteId(residenteId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró historial para el residente " + residenteId));
    }

    @Transactional
    public HistorialMedico guardar(HistorialMedico historial) {
        return historialMedicoRepository.save(historial);
    }

    @Transactional
    public HistorialMedico actualizar(Long id, HistorialMedico datosNuevos){
        HistorialMedico existente=historialMedicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));

        existente.setGrupoSanguineo(datosNuevos.getGrupoSanguineo());
        existente.setAntecedentesClinicos(datosNuevos.getAntecedentesClinicos());
        existente.setAlergias(datosNuevos.getAlergias());
        existente.setDieta(datosNuevos.getDieta());
        existente.setMovilidad(datosNuevos.getMovilidad());

        return historialMedicoRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        historialMedicoRepository.deleteById(id);
    }

    @Transactional
    public List<HistorialMedico> listarTodos(){
        return historialMedicoRepository.findAll();
    }

    @Transactional
    public void actualizarGrupoSanguineo(Long id, String nuevoGrupo) {
        HistorialMedico historial = historialMedicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Historial no encontrado"));
        historial.setGrupoSanguineo(nuevoGrupo);
        historialMedicoRepository.save(historial);
    }

    @Transactional
    public void actualizarAntecedentes(Long id, String nuevoAntecedente) {
        HistorialMedico historial = historialMedicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Historial no encontrado"));
        historial.setAntecedentesClinicos(nuevoAntecedente);
        historialMedicoRepository.save(historial);
    }

    @Transactional
    public void añadirAlergia(Long id, String nuevaAlergia) {
        HistorialMedico historial = historialMedicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Historial no encontrado"));
        historial.getAlergias().add(nuevaAlergia);
        historialMedicoRepository.save(historial);
    }

    @Transactional
    public void borrarAlergia(Long id, String alergiaABorrar) {
        HistorialMedico historial = historialMedicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Historial no encontrado"));
        historial.getAlergias().remove(alergiaABorrar);
        historialMedicoRepository.save(historial);
    }

    @Transactional
    public void actualizarDieta(Long id, String nuevaDieta) {
        HistorialMedico historial = historialMedicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Historial no encontrado"));
        historial.setDieta(nuevaDieta);
        historialMedicoRepository.save(historial);
    }

    @Transactional
    public void actualizarMovilidad(Long id, String nuevoMovilidad) {
        HistorialMedico historial = historialMedicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Historial no encontrado"));
        historial.setMovilidad(nuevoMovilidad);
        historialMedicoRepository.save(historial);
    }
}
