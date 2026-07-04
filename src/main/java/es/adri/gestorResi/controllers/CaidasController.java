package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.registros.Caidas;
import es.adri.gestorResi.service.CaidasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caidas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CaidasController {

    private final CaidasService caidasService;

    @GetMapping
    public ResponseEntity<List<Caidas>> listarTodas() {
        return ResponseEntity.ok(caidasService.mostrarCaidas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caidas> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(caidasService.mostrarCaidaPorId(id));
    }

    @PostMapping
    public ResponseEntity<String> registrarCaida(
            @RequestParam Long empleadoId,
            @RequestParam Long residenteId,
            @RequestBody Caidas caida) {

        caidasService.registrarCaida(empleadoId, residenteId, caida);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro de caída guardado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCaida(@PathVariable Long id) {
        caidasService.borrarCaida(id);
        return ResponseEntity.noContent().build();
    }
}