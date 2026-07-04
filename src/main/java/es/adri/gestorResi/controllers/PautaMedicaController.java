package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.salud.PautaMedica;
import es.adri.gestorResi.service.PautaMedicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/pautas-medicas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PautaMedicaController {

    private final PautaMedicaService pautaMedicaService;

    @PostMapping("/residente/{residenteId}")
    public ResponseEntity<PautaMedica> crearPauta(@PathVariable Long residenteId, @RequestBody PautaMedica nuevaPauta) {
        PautaMedica pautaCreada = pautaMedicaService.crearPauta(residenteId, nuevaPauta);
        return new ResponseEntity<>(pautaCreada, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PautaMedica> obtenerPauta(@PathVariable Long id) {
        PautaMedica pauta = pautaMedicaService.encontrarPauta(id);
        return ResponseEntity.ok(pauta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarPauta(@PathVariable Long id) {
        pautaMedicaService.borrarPauta(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/medicamento")
    public ResponseEntity<Void> editarMedicamento(@PathVariable Long id, @RequestParam String nuevoMedicamento) {
        pautaMedicaService.editarMedicamento(id, nuevoMedicamento);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/dosis")
    public ResponseEntity<Void> editarDosis(@PathVariable Long id, @RequestParam String nuevaDosis) {
        pautaMedicaService.editarDosis(id, nuevaDosis);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/fecha-hora")
    public ResponseEntity<Void> editarFechaHora(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime nuevaFecha) {
        pautaMedicaService.editarFechaHora(id, nuevaFecha);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/duracion")
    public ResponseEntity<Void> editarDuracion(@PathVariable Long id, @RequestParam String nuevaDuracion) {
        pautaMedicaService.editarDuracion(id, nuevaDuracion);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/observaciones")
    public ResponseEntity<Void> editarObservaciones(@PathVariable Long id, @RequestParam String nuevasObservaciones) {
        pautaMedicaService.editarObservaciones(id, nuevasObservaciones);
        return ResponseEntity.ok().build();
    }
}