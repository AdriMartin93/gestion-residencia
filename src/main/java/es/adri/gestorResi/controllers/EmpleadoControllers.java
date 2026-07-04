package es.adri.gestorResi.controllers;


import es.adri.gestorResi.entidades.Dtos.EmpleadoResponseDto;
import es.adri.gestorResi.entidades.enums.Roles;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.security.JwtUtil;
import es.adri.gestorResi.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmpleadoControllers {

    private final EmpleadoService empleadoService;
    private final EmpleadoRepository empleadoRepository;
    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<List<EmpleadoResponseDto>> listarTodos(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        Long empresaId = jwtUtil.extractEmpresaId(jwt);
        return ResponseEntity.ok(empleadoService.mostrarEmpleadosPorEmpresa(empresaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDto> obtenerEmpleadoPorId(@PathVariable Long id) {
        Empleado resultado = empleadoService.findById(id);
        EmpleadoResponseDto responseDto = empleadoService.mapearADto(resultado);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('DIRECTOR')")
    public ResponseEntity<String> registrarEmpleado(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody Empleado nuevoEmpleado) {

        try{
            String jwt = token.substring(7);
            Long empresaIdAdmin = jwtUtil.extractEmpresaId(jwt);
            empleadoService.registrarEmpleadoConEmpresa(nuevoEmpleado, empresaIdAdmin);
            return ResponseEntity.status(HttpStatus.CREATED).body("Empleado creado correctamente");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar el empleado: " + e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id) {
        Empleado empleado = empleadoService.findById(id);

        if (empleado.getRoles().contains(Roles.ROLE_DIRECTOR)) {

            long cantidadDirectores = empleadoRepository.countByEmpresaIdAndRolesContaining(
                    empleado.getEmpresa().getId(),
                    Roles.ROLE_DIRECTOR
            );

            if (cantidadDirectores <= 1) {
                return ResponseEntity.badRequest()
                        .body("Error: No se puede eliminar al empleado. Debe haber al menos un Director/Administrador en la empresa.");
            }
        }

        empleadoService.borrarEmpleado(empleado);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEmpleado(@PathVariable Long id, @Valid @RequestBody Empleado datosActualizados) {
        Empleado empleadoExistente = empleadoService.findById(id);

        if (empleadoExistente.getRoles().contains(Roles.ROLE_DIRECTOR) &&
        !datosActualizados.getRoles().contains(Roles.ROLE_DIRECTOR)) {

            long cantidadDirectores = empleadoRepository.countByEmpresaIdAndRolesContaining(
                    empleadoExistente.getEmpresa().getId(),
                    Roles.ROLE_DIRECTOR
            );
            if(cantidadDirectores <= 1){
                return ResponseEntity.badRequest()
                        .body("Error: No puedes quitar el rol de Director a este usuario. " +
                                "La empresa debe contar con al menos un Administrador/Director activo.");
            }
        }

        empleadoExistente.setNombreUsuario(datosActualizados.getNombreUsuario());
        empleadoExistente.setNombre(datosActualizados.getNombre());
        empleadoExistente.setApellidos(datosActualizados.getApellidos());
        empleadoExistente.setEmail(datosActualizados.getEmail());
        empleadoExistente.setTelefono(datosActualizados.getTelefono());
        empleadoExistente.setDni(datosActualizados.getDni());
        empleadoExistente.setRoles(datosActualizados.getRoles());

        if (datosActualizados.getPassword() != null && !datosActualizados.getPassword().isBlank()) {
            empleadoExistente.setPassword(datosActualizados.getPassword());
        }

        empleadoService.registrarEmpleado(empleadoExistente);
        return ResponseEntity.ok("Empleado actualizado correctamente");
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Empleado>> listarPorRoles(@RequestParam Roles rol) {
        return ResponseEntity.ok(empleadoService.mostrarEmpleadosByRoles(rol));
    }
}