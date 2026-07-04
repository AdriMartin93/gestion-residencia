package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.enums.trabajoSocial.CategoriaSocial;
import es.adri.gestorResi.entidades.enums.trabajoSocial.EstadoTramite;
import es.adri.gestorResi.entidades.registros.RegistroSocial;
import es.adri.gestorResi.service.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/registros-sociales")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RegistroSocialController {

    private final SocialService socialService;

    @GetMapping
    public ResponseEntity<List<RegistroSocial>> listarTodos() {
        return ResponseEntity.ok(socialService.mostrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroSocial> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(socialService.mostrarPorId(id));
    }

    @GetMapping("/residente/{residenteId}")
    public ResponseEntity<List<RegistroSocial>> listarPorResidente(@PathVariable Long residenteId) {
        return ResponseEntity.ok(socialService.mostrarPorResidente(residenteId));
    }

    @PostMapping
    public ResponseEntity<RegistroSocial> crearRegistro(
            @RequestParam Long residenteId,
            @RequestParam Long empleadoId,
            @RequestBody RegistroSocial nuevoRegistro) {

        RegistroSocial creado = socialService.crearRegistro(residenteId, empleadoId, nuevoRegistro);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable Long id) {
        socialService.borrarRegistro(id);
        return ResponseEntity.noContent().build();
    }

    // ENDPOINTS DE MODIFICACIÓN PARCIAL (PATCH)

    @PatchMapping("/{id}/estado")
    public ResponseEntity<String> actualizarEstado(
            @PathVariable Long id,
            @RequestParam EstadoTramite nuevoEstado) {
        socialService.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok("Estado del trámite actualizado correctamente");
    }

    @PatchMapping("/{id}/categoria")
    public ResponseEntity<String> editarCategoria(
            @PathVariable Long id,
            @RequestParam CategoriaSocial nuevaCategoria) {
        socialService.editarCategoria(id, nuevaCategoria);
        return ResponseEntity.ok("Categoría social modificada correctamente");
    }

    @PatchMapping("/{id}/gestiones")
    public ResponseEntity<String> editarGestiones(
            @PathVariable Long id,
            @RequestBody String nuevasGestiones) {
        socialService.editarGestiones(id, nuevasGestiones);
        return ResponseEntity.ok("Gestiones realizadas actualizadas correctamente");
    }

    @PatchMapping("/{id}/fechas-tramite")
    public ResponseEntity<String> actualizarFechasTramite(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate presentacion,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vencimiento) {
        socialService.actualizarFechasTramite(id, presentacion, vencimiento);
        return ResponseEntity.ok("Fechas de presentación y vencimiento actualizadas correctamente");
    }

    @PatchMapping("/{id}/alerta")
    public ResponseEntity<String> conmutarAlertaSocial(
            @PathVariable Long id,
            @RequestParam boolean estadoAlerta) {
        socialService.conmutarAlertaSocial(id, estadoAlerta);
        return ResponseEntity.ok("Estado de la alerta social conmutado correctamente");
    }

    @PatchMapping("/{id}/numero-expediente")
    public ResponseEntity<String> editarNumeroExpediente(
            @PathVariable Long id,
            @RequestParam String numExpediente) {
        socialService.editarNumeroExpediente(id, numExpediente);
        return ResponseEntity.ok("Número de expediente actualizado correctamente");
    }
}