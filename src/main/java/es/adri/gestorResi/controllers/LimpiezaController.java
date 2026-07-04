package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.registros.limpieza.*;
import es.adri.gestorResi.service.LimpiezaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/limpiezas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LimpiezaController {

    private final LimpiezaService limpiezaService;

    //ENDPOINTS DE LECTURA Y ELIMINACIÓN

    @GetMapping
    public ResponseEntity<List<LimpiezaBase>> listarTodas() {
        return ResponseEntity.ok(limpiezaService.mostrarTodosLosRegistros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LimpiezaBase> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(limpiezaService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegistro(@PathVariable Long id) {
        limpiezaService.borrarRegistro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<LimpiezaBase>> listarPorEmpleado(@PathVariable Long empleadoId) {
        return ResponseEntity.ok(limpiezaService.mostrarPorEmpleado(empleadoId));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<LimpiezaBase>> listarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(limpiezaService.mostrarPorFecha(fecha));
    }

    //ENDPOINTS DE CREACIÓN ESPECÍFICOS

    @PostMapping("/clinica")
    public ResponseEntity<LimpiezaBase> crearClinica(@RequestBody LimpiezaClin registro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(limpiezaService.guardarRegistro(registro));
    }

    @PostMapping("/comun")
    public ResponseEntity<LimpiezaBase> crearComun(@RequestBody LimpiezaComun registro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(limpiezaService.guardarRegistro(registro));
    }

    @PostMapping("/habitacion")
    public ResponseEntity<LimpiezaBase> crearHabitacion(@RequestBody LimpiezaHab registro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(limpiezaService.guardarRegistro(registro));
    }

    @PostMapping("/ropa")
    public ResponseEntity<LimpiezaBase> crearRopa(@RequestBody LimpiezaRopa registro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(limpiezaService.guardarRegistro(registro));
    }
}