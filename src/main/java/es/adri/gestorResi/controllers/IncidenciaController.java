package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.registros.Incidencia;
import es.adri.gestorResi.service.IncidenciasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class IncidenciaController {

    private final IncidenciasService incidenciasService;

    @GetMapping
    public ResponseEntity<List<Incidencia>> listarTodas() {
        return ResponseEntity.ok(incidenciasService.mostrarIncidencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(incidenciasService.mostrarIncidenciaById(id));
    }

    @GetMapping("/residente/{residenteId}")
    public ResponseEntity<List<Incidencia>> listarPorResidente(@PathVariable Long residenteId) {
        return ResponseEntity.ok(incidenciasService.mostrarIncidenciasByResidenteId(residenteId));
    }

    @PostMapping
    public ResponseEntity<String> registrarIncidencia(@RequestBody Incidencia incidencia) {
        incidenciasService.registrarIncidencia(incidencia);
        return ResponseEntity.status(HttpStatus.CREATED).body("Incidencia registrada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarIncidencia(@PathVariable Long id) {
        incidenciasService.borrarIncidencia(id);
        return ResponseEntity.noContent().build();
    }
}
