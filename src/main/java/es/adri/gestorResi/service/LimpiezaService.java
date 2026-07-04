package es.adri.gestorResi.service;

import es.adri.gestorResi.entidades.registros.limpieza.LimpiezaBase;
import es.adri.gestorResi.repositorio.LimpiezaBaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LimpiezaService {

    private final LimpiezaBaseRepository limpiezaRepository;

    public LimpiezaBase findById(Long id) {
        return limpiezaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de limpieza no encontrado"));
    }


    @Transactional
    public LimpiezaBase guardarRegistro(LimpiezaBase registro) {

        if (registro.getFecha() == null) {
            registro.setFecha(LocalDate.now());
        }
        return limpiezaRepository.save(registro);
    }


    @Transactional
    public void borrarRegistro(Long id) {
        LimpiezaBase registro = findById(id);
        limpiezaRepository.delete(registro);
    }


    public List<LimpiezaBase> mostrarTodosLosRegistros() {
        return limpiezaRepository.findAll();
    }


    public List<LimpiezaBase> mostrarPorEmpleado(Long empleadoId) {
        return limpiezaRepository.findByEmpleadoId(empleadoId);
    }

    public List<LimpiezaBase> mostrarPorFecha(LocalDate fecha) {
        return limpiezaRepository.findByFecha(fecha);
    }
}