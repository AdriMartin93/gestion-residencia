package es.adri.gestorResi.controllers;


import es.adri.gestorResi.entidades.registros.diariosRes.Evacuaciones;
import es.adri.gestorResi.entidades.registros.diariosRes.Higiene;
import es.adri.gestorResi.entidades.registros.diariosRes.CambioPostural;
import es.adri.gestorResi.service.CuidadosService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cuidados")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CuidadosController {

    private final CuidadosService cuidadosService;

    // --- ENDPOINTS PARA EVACUACIONES ---

    @PostMapping("/evacuaciones/residente/{residenteId}/empleado/{empleadoId}")
    public ResponseEntity<String> registrarEvacuacion(
            @PathVariable Long residenteId,
            @PathVariable Long empleadoId,
            @RequestBody Evacuaciones registro) {
        cuidadosService.registrarEvacuacion(residenteId, empleadoId, registro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro de evacuación creado");
    }

    @DeleteMapping("/evacuaciones/{id}")
    public ResponseEntity<Void> borrarEvacuacion(@PathVariable Long id) {
        cuidadosService.borrarEvacuacion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/evacuaciones")
    public ResponseEntity<List<Evacuaciones>> listarEvacuaciones(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        if (fecha != null) {
            return ResponseEntity.ok(cuidadosService.listarEvacuacionesPorFecha(fecha));
        }
        return ResponseEntity.ok(cuidadosService.listarEvacuaciones());
    }

    @GetMapping("/evacuaciones/residente/{residenteId}")
    public ResponseEntity<List<Evacuaciones>> listarEvacuacionesPorResidente(
            @PathVariable Long residenteId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        if (fecha != null) {
            return ResponseEntity.ok(cuidadosService.listarEvacuacionesResidentePorDia(residenteId, fecha));
        }
        return ResponseEntity.ok(cuidadosService.listarEvacuacionesPorResidente(residenteId));
    }

    // --- ENDPOINTS PARA HIGIENE ---

    @PostMapping("/higiene/residente/{residenteId}/empleado/{empleadoId}")
    public ResponseEntity<String> registrarHigiene(
            @PathVariable Long residenteId,
            @PathVariable Long empleadoId,
            @RequestBody Higiene registro) {
        cuidadosService.registrarHigiene(residenteId, empleadoId, registro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro de higiene creado");
    }

    @DeleteMapping("/higiene/{id}")
    public ResponseEntity<Void> borrarHigiene(@PathVariable Long id) {
        cuidadosService.borrarHigiene(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/higiene")
    public ResponseEntity<List<Higiene>> listarHigiene(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        if (fecha != null) {
            return ResponseEntity.ok(cuidadosService.listarHigienePorFecha(fecha));
        }
        return ResponseEntity.ok(cuidadosService.listarHigiene());
    }

    @GetMapping("/higiene/residente/{residenteId}")
    public ResponseEntity<List<Higiene>> listarHigienePorResidente(
            @PathVariable Long residenteId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        if (fecha != null) {
            return ResponseEntity.ok(cuidadosService.listarHigieneResidentePorDia(residenteId, fecha));
        }
        return ResponseEntity.ok(cuidadosService.listarHigienePorResidente(residenteId));
    }

    // --- ENDPOINTS PARA CAMBIOS POSTURALES ---

    @PostMapping("/cambios-posturales/residente/{residenteId}/empleado/{empleadoId}")
    public ResponseEntity<String> registrarCambioPostural(
            @PathVariable Long residenteId,
            @PathVariable Long empleadoId,
            @RequestBody CambioPostural registro) {
        cuidadosService.registrarCambioPostural(residenteId, empleadoId, registro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cambio postural registrado");
    }

    @DeleteMapping("/cambios-posturales/{id}")
    public ResponseEntity<Void> borrarCambioPostural(@PathVariable Long id) {
        cuidadosService.borrarCambioPostural(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cambios-posturales")
    public ResponseEntity<List<CambioPostural>> listarCambios(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        if (fecha != null) {
            return ResponseEntity.ok(cuidadosService.listarCambioPorFecha(fecha));
        }
        return ResponseEntity.ok(cuidadosService.listarCambios());
    }

    @GetMapping("/cambios-posturales/residente/{residenteId}")
    public ResponseEntity<List<CambioPostural>> listarCambiosPorResidente(
            @PathVariable Long residenteId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        if (fecha != null) {
            return ResponseEntity.ok(cuidadosService.listarCambioResidentePorDia(residenteId, fecha));
        }
        return ResponseEntity.ok(cuidadosService.listarCambioPorResidente(residenteId));
    }
}