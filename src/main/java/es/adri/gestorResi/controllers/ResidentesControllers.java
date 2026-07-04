package es.adri.gestorResi.controllers;


import es.adri.gestorResi.entidades.personas.Contacto;
import es.adri.gestorResi.entidades.personas.Residente;
import es.adri.gestorResi.service.ResidenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/residentes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ResidentesControllers {

    private final ResidenteService residenteService;

    @GetMapping
    public ResponseEntity<List<Residente>> listarTodos() {
        List<Residente> residentes = residenteService.listarTodos();
        return ResponseEntity.ok(residentes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Residente> obtenerPorId(@PathVariable Long id) {
        Residente residente = residenteService.buscarPorId(id);
        return ResponseEntity.ok(residente);
    }


    @PostMapping
    public ResponseEntity<Residente> registrar(@RequestBody Residente residente) {
        Residente nuevo = residenteService.registrarResidente(residente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<String> actualizarCamposIndividuales(
            @PathVariable Long id,
            @RequestBody Map<String, Object> campos) {

        try {
            residenteService.actualizarParcial(id, campos);
            return ResponseEntity.ok("Residente actualizado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error en los campos enviados: " + e.getLocalizedMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Residente residente = residenteService.buscarPorId(id);
        residenteService.borrarResidente(residente);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/contactos")
    public ResponseEntity<Void> anyadirContacto(@PathVariable Long id, @RequestBody Contacto contacto) {
        residenteService.agregarContacto(id, contacto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/contactos")
    public ResponseEntity<Void> eliminarContacto(@PathVariable Long id, @RequestBody Contacto contacto) {
        residenteService.borrarContacto(id, contacto);
        return ResponseEntity.noContent().build();
    }
}
