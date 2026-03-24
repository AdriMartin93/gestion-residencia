package es.adri.gestorResi.controllers;


import es.adri.gestorResi.entidades.enums.Roles;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoControllers {

    private final EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> listarTodos() {
        return ResponseEntity.ok(empleadoService.mostrarEmpleados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id) {
        Empleado resultado = empleadoService.findById(id);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping
    public ResponseEntity<String> registrarEmpleado(@RequestBody Empleado empleado) {
        empleadoService.registrarEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body("Empleado creado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        Empleado empleado = empleadoService.findById(id);
        empleadoService.borrarEmpleado(empleado);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado datosActualizados) {
        Empleado empleadoExistente = empleadoService.findById(id);


        empleadoExistente.setNombre(datosActualizados.getNombre());
        empleadoExistente.setApellidos(datosActualizados.getApellidos());
        empleadoExistente.setEmail(datosActualizados.getEmail());

        empleadoService.registrarEmpleado(empleadoExistente); // El save de Spring Data hace update si el ID existe
        return ResponseEntity.ok("Empleado actualizado");
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Empleado>> listarPorRoles(@RequestParam Roles rol) {
        return ResponseEntity.ok(empleadoService.mostrarEmpleadosByRoles(rol));
    }
}
