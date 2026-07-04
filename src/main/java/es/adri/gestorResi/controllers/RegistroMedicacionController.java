package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.enums.EstadoTarea;
import es.adri.gestorResi.entidades.registros.diariosRes.RegistroMedicacion;
import es.adri.gestorResi.service.RegistroMedicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/medicacion-registros")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RegistroMedicacionController {

    private final RegistroMedicacionService medicacionService;

    @GetMapping
    public ResponseEntity<List<RegistroMedicacion>> obtenerTodos() {
        return ResponseEntity.ok(medicacionService.mostrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroMedicacion> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicacionService.mostrarPorId(id));
    }

    @GetMapping("/residente/{residenteId}")
    public ResponseEntity<List<RegistroMedicacion>> obtenerPorResidente(@PathVariable Long residenteId) {
        return ResponseEntity.ok(medicacionService.mostrarPorResidente(residenteId));
    }

    @PostMapping("/pauta/{pautaId}/residente/{residenteId}/empleado/{empleadoId}")
    public ResponseEntity<RegistroMedicacion> registrarToma(
            @PathVariable Long pautaId,
            @PathVariable Long residenteId,
            @PathVariable Long empleadoId,
            @RequestBody RegistroMedicacion nuevo) {
        RegistroMedicacion guardado = medicacionService.registrarToma(pautaId, residenteId, empleadoId, nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable Long id) {
        medicacionService.borrarRegistro(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<String> actualizarEstado(
            @PathVariable Long id,
            @RequestParam EstadoTarea estado) {
        medicacionService.actualizarEstadoTarea(id, estado);
        return ResponseEntity.ok("Estado de la tarea actualizado correctamente");
    }

    @PatchMapping("/{id}/observaciones")
    public ResponseEntity<String> editarObservaciones(
            @PathVariable Long id,
            @RequestBody String observaciones) {
        medicacionService.editarObservaciones(id, observaciones);
        return ResponseEntity.ok("Observaciones modificadas correctamente");
    }

    @PatchMapping("/{id}/fecha-hora")
    public ResponseEntity<String> corregirFechaHora(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHora) {
        medicacionService.corregirFechaHoraReal(id, fechaHora);
        return ResponseEntity.ok("Fecha y hora de la toma corregidas correctamente");
    }
}