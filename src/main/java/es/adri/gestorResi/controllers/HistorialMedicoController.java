package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.salud.HistorialMedico;
import es.adri.gestorResi.service.HistorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historiales-medicos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HistorialMedicoController {

    private final HistorialService historialService;

    @GetMapping
    public ResponseEntity<List<HistorialMedico>> listarTodos() {
        List<HistorialMedico> historiales = historialService.listarTodos();
        return ResponseEntity.ok(historiales);
    }

    @GetMapping("/residente/{residenteId}")
    public ResponseEntity<HistorialMedico> obtenerPorResidente(@PathVariable Long residenteId) {
        HistorialMedico historial = historialService.obtenerPorResidente(residenteId);
        return ResponseEntity.ok(historial);
    }

    @PostMapping
    public ResponseEntity<HistorialMedico> guardar(@RequestBody HistorialMedico historial) {
        HistorialMedico historialGuardado = historialService.guardar(historial);
        return new ResponseEntity<>(historialGuardado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialMedico> actualizar(@PathVariable Long id, @RequestBody HistorialMedico datosNuevos) {
        HistorialMedico historialActualizado = historialService.actualizar(id, datosNuevos);
        return ResponseEntity.ok(historialActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        historialService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/grupo-sanguineo")
    public ResponseEntity<Void> actualizarGrupoSanguineo(@PathVariable Long id, @RequestParam String nuevoGrupo) {
        historialService.actualizarGrupoSanguineo(id, nuevoGrupo);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/antecedentes")
    public ResponseEntity<Void> actualizarAntecedentes(@PathVariable Long id, @RequestParam String nuevoAntecedente) {
        historialService.actualizarAntecedentes(id, nuevoAntecedente);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/alergias")
    public ResponseEntity<Void> anadirAlergia(@PathVariable Long id, @RequestParam String nuevaAlergia) {
        historialService.añadirAlergia(id, nuevaAlergia);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/alergias")
    public ResponseEntity<Void> borrarAlergia(@PathVariable Long id, @RequestParam String alergiaABorrar) {
        historialService.borrarAlergia(id, alergiaABorrar);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/dieta")
    public ResponseEntity<Void> actualizarDieta(@PathVariable Long id, @RequestParam String nuevaDieta) {
        historialService.actualizarDieta(id, nuevaDieta);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/movilidad")
    public ResponseEntity<Void> actualizarMovilidad(@PathVariable Long id, @RequestParam String nuevoMovilidad) {
        historialService.actualizarMovilidad(id, nuevoMovilidad);
        return ResponseEntity.ok().build();
    }
}