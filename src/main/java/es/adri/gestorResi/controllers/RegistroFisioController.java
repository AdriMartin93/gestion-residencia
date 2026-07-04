package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.enums.ActividadFisio;
import es.adri.gestorResi.entidades.registros.RegistroFisio;
import es.adri.gestorResi.service.FisioService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/registros-fisio")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RegistroFisioController {

    private final FisioService fisioService;

    @GetMapping
    public ResponseEntity<List<RegistroFisio>> listarTodos() {
        return ResponseEntity.ok(fisioService.mostrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroFisio> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(fisioService.mostrarPorId(id));
    }

    @GetMapping("/residente/{residenteId}")
    public ResponseEntity<List<RegistroFisio>> listarPorResidente(@PathVariable Long residenteId) {
        return ResponseEntity.ok(fisioService.mostrarPorResidente(residenteId));
    }

    @PostMapping
    public ResponseEntity<RegistroFisio> crearRegistro(
            @RequestParam Long empleadoId,
            @RequestParam List<Long> residentesIds,
            @RequestBody RegistroFisio nuevoRegistro) {

        RegistroFisio creado = fisioService.crearRegistro(empleadoId, residentesIds, nuevoRegistro);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable Long id) {
        fisioService.borrarRegistro(id);
        return ResponseEntity.noContent().build();
    }

    // ENDPOINTS DE MODIFICACIÓN PARCIAL (PATCH)

    @PatchMapping("/{id}/actividad")
    public ResponseEntity<String> editarActividad(
            @PathVariable Long id,
            @RequestParam ActividadFisio nuevaActividad) {
        fisioService.editarActividad(id, nuevaActividad);
        return ResponseEntity.ok("Actividad de fisioterapia modificada correctamente");
    }

    @PatchMapping("/{id}/fecha")
    public ResponseEntity<String> editarFecha(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate nuevaFecha) {
        fisioService.editarFecha(id, nuevaFecha);
        return ResponseEntity.ok("Fecha de la sesión modificada correctamente");
    }

    @PatchMapping("/{id}/observaciones")
    public ResponseEntity<String> editarObservaciones(
            @PathVariable Long id,
            @RequestBody String nuevasObs) {
        fisioService.editarObservaciones(id, nuevasObs);
        return ResponseEntity.ok("Observaciones actualizadas correctamente");
    }

    // GESTIÓN DINÁMICA DE PARTICIPANTES EN LA SESIÓN

    @PostMapping("/{sesionId}/residentes")
    public ResponseEntity<String> añadirResidenteASesion(
            @PathVariable Long sesionId,
            @RequestParam Long residenteId) {
        fisioService.añadirResidenteASesion(sesionId, residenteId);
        return ResponseEntity.ok("Residente integrado en la sesión de fisio con éxito");
    }

    @DeleteMapping("/{sesionId}/residentes/{residenteId}")
    public ResponseEntity<String> quitarResidenteDeSesion(
            @PathVariable Long sesionId,
            @PathVariable Long residenteId) {
        fisioService.quitarResidenteDeSesion(sesionId, residenteId);
        return ResponseEntity.ok("Residente removido de la sesión de fisio con éxito");
    }
}
