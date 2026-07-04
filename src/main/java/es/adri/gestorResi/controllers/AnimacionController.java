package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.registros.RegistroAnimacion;
import es.adri.gestorResi.service.AnimacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/registros-animacion")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AnimacionController {

    private final AnimacionService animacionService;

    @GetMapping
    public ResponseEntity<List<RegistroAnimacion>> listarTodos() {
        return ResponseEntity.ok(animacionService.mostrarTodos());
    }

    @PostMapping
    public ResponseEntity<RegistroAnimacion> crearRegistro(
            @RequestParam Long empleadoId,
            @RequestParam List<Long> residentesIds,
            @RequestBody RegistroAnimacion nuevoRegistro) {

        RegistroAnimacion creado = animacionService.crearRegistro(empleadoId, residentesIds, nuevoRegistro);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable Long id) {
        animacionService.borrarRegistro(id);
        return ResponseEntity.noContent().build();
    }

    // ENDPOINTS DE MODIFICACIÓN DE DATOS BÁSICOS

    @PatchMapping("/{id}/fecha-hora")
    public ResponseEntity<String> editarFechaHora(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime nuevaFecha) {
        animacionService.editarFechaHora(id, nuevaFecha);
        return ResponseEntity.ok("Fecha y hora actualizadas correctamente");
    }

    @PatchMapping("/{id}/actividad")
    public ResponseEntity<String> editarActividad(
            @PathVariable Long id,
            @RequestBody String nuevaActividad) {
        animacionService.editarActividad(id, nuevaActividad);
        return ResponseEntity.ok("Actividad actualizada correctamente");
    }

    @PatchMapping("/{id}/observaciones")
    public ResponseEntity<String> editarObservaciones(
            @PathVariable Long id,
            @RequestBody String nuevasObservaciones) {
        animacionService.editarObservaciones(id, nuevasObservaciones);
        return ResponseEntity.ok("Observaciones actualizadas correctamente");
    }

    // ENDPOINTS DE GESTIÓN DE PARTICIPANTES (RESIDENTES)

    @PostMapping("/{id}/participantes")
    public ResponseEntity<String> anyadirParticipante(
            @PathVariable Long id,
            @RequestParam Long residenteId) {
        animacionService.anyadirParticipante(id, residenteId);
        return ResponseEntity.ok("Participante añadido correctamente al registro");
    }

    @DeleteMapping("/{id}/participantes/{residenteId}")
    public ResponseEntity<String> quitarParticipante(
            @PathVariable Long id,
            @PathVariable Long residenteId) {
        animacionService.quitarParticipante(id, residenteId);
        return ResponseEntity.ok("Participante eliminado del registro correctamente");
    }

    @PutMapping("/{id}/participantes")
    public ResponseEntity<String> actualizarListaParticipantes(
            @PathVariable Long id,
            @RequestParam List<Long> residentesIds) {
        animacionService.actualizarListaParticipantes(id, residentesIds);
        return ResponseEntity.ok("Lista completa de participantes actualizada correctamente");
    }
}