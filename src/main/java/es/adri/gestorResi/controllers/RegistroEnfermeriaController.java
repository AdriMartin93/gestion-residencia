package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.enums.AccionEnfermeria;
import es.adri.gestorResi.entidades.registros.RegistroEnfermeria;
import es.adri.gestorResi.service.EnfermeriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/registros-enfermeria")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RegistroEnfermeriaController {

    private final EnfermeriaService enfermeriaService;

    @GetMapping
    public ResponseEntity<List<RegistroEnfermeria>> listarTodos() {
        return ResponseEntity.ok(enfermeriaService.mostrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroEnfermeria> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(enfermeriaService.mostrarPorId(id));
    }

    @GetMapping("/residente/{residenteId}")
    public ResponseEntity<List<RegistroEnfermeria>> listarPorResidente(@PathVariable Long residenteId) {
        return ResponseEntity.ok(enfermeriaService.mostrarPorResidente(residenteId));
    }

    @PostMapping
    public ResponseEntity<RegistroEnfermeria> crearRegistro(
            @RequestParam Long residenteId,
            @RequestParam Long enfermeroId,
            @RequestBody RegistroEnfermeria nuevoRegistro) {

        RegistroEnfermeria creado = enfermeriaService.crearRegistro(residenteId, enfermeroId, nuevoRegistro);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable Long id) {
        enfermeriaService.borrarRegistro(id);
        return ResponseEntity.noContent().build();
    }

    // ENDPOINTS DE ACTUALIZACIÓN PARCIAL (PATCH)

    @PatchMapping("/{id}/fecha-hora")
    public ResponseEntity<String> editarFechaHora(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime nuevaFecha) {
        enfermeriaService.editarFechaHora(id, nuevaFecha);
        return ResponseEntity.ok("Fecha y hora actualizadas correctamente");
    }

    @PatchMapping("/{id}/tipo-accion")
    public ResponseEntity<String> editarTipoAccion(
            @PathVariable Long id,
            @RequestParam AccionEnfermeria nuevaAccion) {
        enfermeriaService.editarTipoAccion(id, nuevaAccion);
        return ResponseEntity.ok("Tipo de acción actualizado correctamente");
    }

    @PatchMapping("/{id}/observacion")
    public ResponseEntity<String> editarObservacion(
            @PathVariable Long id,
            @RequestBody String nuevaObservacion) {
        enfermeriaService.editarObservacion(id, nuevaObservacion);
        return ResponseEntity.ok("Observación actualizada correctamente");
    }
}
