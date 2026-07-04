package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.registros.ControlCocina;
import es.adri.gestorResi.service.CocinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controles-cocina")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CocinaController {

    private final CocinaService cocinaService;

    @GetMapping
    public ResponseEntity<List<ControlCocina>> listarTodos() {
        return ResponseEntity.ok(cocinaService.mostrarControles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ControlCocina> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cocinaService.buscarControlCocina(id));
    }

    @PostMapping
    public ResponseEntity<String> registrarControlCocina(@RequestBody ControlCocina controlCocina) {
        cocinaService.agregarControlCocina(controlCocina);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro de control de cocina guardado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarControlCocina(@PathVariable Long id) {
        cocinaService.borrarControlCocina(id);
        return ResponseEntity.noContent().build();
    }
}