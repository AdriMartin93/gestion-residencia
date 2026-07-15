package es.adri.gestorResi.controllers;


import es.adri.gestorResi.entidades.Dtos.AuthResponse;
import es.adri.gestorResi.entidades.Dtos.LoginRequest;
import es.adri.gestorResi.entidades.Dtos.RegistroEmpresaDto;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.security.JwtUtil;
import es.adri.gestorResi.service.EmpleadoService;
import es.adri.gestorResi.service.EmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final EmpleadoService empleadoService;
    private final JwtUtil jwtUtil;
    private final EmpresaService empresaService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getNombreUsuario(),
                            loginRequest.getPassword()
                    )
            );

            Empleado empleado = empleadoService.findByNombreUsuario(authentication.getName());

            List<String> rolesString = empleado.getRoles().stream()
                    .map(rol -> rol.name())
                    .collect(Collectors.toList());

            String token = jwtUtil.generateToken(authentication.getName(), empleado.getEmpresa().getId(), rolesString);

            return ResponseEntity.ok(new AuthResponse(token));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas: " + e.getMessage());
        }
    }

    @PostMapping("/register-company")
    public ResponseEntity<String> registrarEmpresaYAdmin(@Valid @RequestBody RegistroEmpresaDto registroDto){
        try{
            empresaService.registrarEmpresaYAdministrador(registroDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Empresa y administrador registrados con éxito.");
        }catch(Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error al procesar el registro: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registrar(@Valid @RequestBody Empleado empleado) {
        try {
            empleadoService.registrarEmpleado(empleado);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con éxito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar el usuario: " + e.getMessage());
        }
    }
}
