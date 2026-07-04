package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.enums.psicologia.CategoriaActividad;
import es.adri.gestorResi.entidades.enums.psicologia.TipoRegistro;
import es.adri.gestorResi.entidades.registros.RegistroPsicologia;
import es.adri.gestorResi.service.PsicologiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/registros-psicologia")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RegistroPsicologiaController {

    private final PsicologiaService psicologiaService;

    @GetMapping
    public ResponseEntity<List<RegistroPsicologia>> listarTodos() {
        return ResponseEntity.ok(psicologiaService.mostrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroPsicologia> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(psicologiaService.mostrarPorId(id));
    }

    @GetMapping("/residente/{residenteId}")
    public ResponseEntity<List<RegistroPsicologia>> listarPorResidente(@PathVariable Long residenteId) {
        return ResponseEntity.ok(psicologiaService.mostrarPorResidente(residenteId));
    }

    @PostMapping
    public ResponseEntity<RegistroPsicologia> crearRegistro(
            @RequestParam Long empleadoId,
            @RequestParam List<Long> residentesIds,
            @RequestBody RegistroPsicologia nuevoRegistro) {

        RegistroPsicologia creado = psicologiaService.crearRegistro(empleadoId, residentesIds, nuevoRegistro);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable Long id) {
        psicologiaService.borrarRegistro(id);
        return ResponseEntity.noContent().build();
    }

    // ENDPOINTS DE MODIFICACIÓN PARCIAL (PATCH)

    @PatchMapping("/{id}/fecha")
    public ResponseEntity<String> editarFecha(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime nuevaFecha) {
        psicologiaService.editarFecha(id, nuevaFecha);
        return ResponseEntity.ok("Fecha actualizada correctamente");
    }

    @PatchMapping("/{id}/tipo-registro")
    public ResponseEntity<String> editarTipoRegistro(
            @PathVariable Long id,
            @RequestParam TipoRegistro nuevoTipo) {
        psicologiaService.editarTipoRegistro(id, nuevoTipo);
        return ResponseEntity.ok("Tipo de registro actualizado correctamente");
    }

    @PatchMapping("/{id}/categoria")
    public ResponseEntity<String> editarCategoria(
            @PathVariable Long id,
            @RequestParam CategoriaActividad nuevaCategoria) {
        psicologiaService.editarCategoria(id, nuevaCategoria);
        return ResponseEntity.ok("Categoría de actividad actualizada correctamente");
    }

    @PatchMapping("/{id}/descripcion")
    public ResponseEntity<String> editarDescripcion(
            @PathVariable Long id,
            @RequestBody String nuevaDesc) {
        psicologiaService.editarDescripcion(id, nuevaDesc);
        return ResponseEntity.ok("Descripción modificada correctamente");
    }

    // ENLACES PARA MANIPULACIÓN DE PARTICIPANTES

    @PostMapping("/{id}/residentes")
    public ResponseEntity<String> añadirResidente(
            @PathVariable Long id,
            @RequestParam Long residenteId) {
        psicologiaService.añadirResidente(id, residenteId);
        return ResponseEntity.ok("Residente añadido correctamente a la sesión");
    }

    @DeleteMapping("/{id}/residentes/{residenteId}")
    public ResponseEntity<String> quitarResidente(
            @PathVariable Long id,
            @PathVariable Long residenteId) {
        psicologiaService.quitarResidente(id, residenteId);
        return ResponseEntity.ok("Residente eliminado de la sesión correctamente");
    }
}