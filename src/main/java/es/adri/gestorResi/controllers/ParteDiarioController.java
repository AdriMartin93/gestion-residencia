package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.registros.ParteDiario;
import es.adri.gestorResi.service.ParteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partes-diarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ParteDiarioController {

    private final ParteService parteService;

    @GetMapping
    public ResponseEntity<List<ParteDiario>> listarTodos() {
        return ResponseEntity.ok(parteService.mostrarPartes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParteDiario> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(parteService.mostrarParteById(id));
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<ParteDiario>> listarPorEmpleado(@PathVariable Long empleadoId) {
        return ResponseEntity.ok(parteService.mostrarParteByCreador_Id(empleadoId));
    }

    @PostMapping
    public ResponseEntity<String> registrarParte(@RequestBody ParteDiario parteDiario) {
        parteService.registrarParte(parteDiario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Parte diario guardado correctamente");
    }

    @PatchMapping("/{id}/contenido")
    public ResponseEntity<String> actualizarContenido(
            @PathVariable Long id,
            @RequestBody String contenidoCorregido) {

        parteService.actualizarContenido(id, contenidoCorregido);
        return ResponseEntity.ok("Contenido del parte diario actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarParte(@PathVariable Long id) {
        parteService.borrarParte(id);
        return ResponseEntity.noContent().build();
    }
}
