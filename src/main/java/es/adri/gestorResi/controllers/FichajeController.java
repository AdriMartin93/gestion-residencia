package es.adri.gestorResi.controllers;

import es.adri.gestorResi.entidades.registros.Fichaje;
import es.adri.gestorResi.service.FichajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/fichajes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FichajeController {

    private final FichajeService fichajeService;


    @PostMapping("/alternar")
    public ResponseEntity<String> alternarFichaje(Principal principal) {
        String username = principal.getName();
        String resultado = fichajeService.alternarFichaje(username);
        return ResponseEntity.ok(resultado);
    }


    @GetMapping("/estado")
    public ResponseEntity<Boolean> comprobarEstado(Principal principal) {
        String username = principal.getName();
        boolean trabajando = fichajeService.estaTrabajandoActualmente(username);
        return ResponseEntity.ok(trabajando);
    }


    @GetMapping
    public ResponseEntity<List<Fichaje>> listarTodos() {
        return ResponseEntity.ok(fichajeService.obtenerTodosLosFichajes());
    }
}