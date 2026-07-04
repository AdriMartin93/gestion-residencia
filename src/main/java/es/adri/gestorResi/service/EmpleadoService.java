package es.adri.gestorResi.service;


import es.adri.gestorResi.entidades.Dtos.EmpleadoResponseDto;
import es.adri.gestorResi.entidades.enums.Roles;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Empresa;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.EmpresaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpleadoService implements UserDetailsService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpresaRepository empresaRepository;
    private final PasswordEncoder passwordEncoder;

    public Empleado findById(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public List<EmpleadoResponseDto> mostrarEmpleadosPorEmpresa(Long empresaId) {
        return empleadoRepository.findByEmpresaId(empresaId).stream()
                .map(this::mapearADto)
                .toList();
    }

    public Empleado findByNombreUsuario(String nombreUsuario) {
        return empleadoRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con el nombre de usuario: " + nombreUsuario));
    }

    @Transactional
    public void registrarEmpleado(Empleado empleado) {

        if (empleado.getId() == null){
            if (empleadoRepository.existsByNombreUsuario(empleado.getNombreUsuario())) {
                throw new RuntimeException("El nombre de usuario ya existe");
            }
            if(empleadoRepository.existsByDni(empleado.getDni())){
                throw new RuntimeException("El dni ya esta registrado");
            }
            if(empleadoRepository.existsByEmail(empleado.getEmail())){
                throw new RuntimeException("El email ya esta registrado");
            }

        }
        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        empleadoRepository.save(empleado);
    }

    @Transactional
    public void registrarEmpleadoConEmpresa(Empleado empleado, Long empresaId){
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("La empresa del administrador no existe"));
        empleado.setEmpresa(empresa);

        if(empleadoRepository.existsByNombreUsuario(empleado.getNombreUsuario())){
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        if(empleadoRepository.existsByDni(empleado.getDni())){
            throw new RuntimeException("El dni ya esta registrado");
        }
        if(empleadoRepository.existsByEmail(empleado.getEmail())){
            throw new RuntimeException("El email ya esta registrado");
        }
        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        empleadoRepository.save(empleado);

    }

    public void borrarEmpleado(Empleado empleado) {
        empleadoRepository.delete(empleado);
    }

    public List<Empleado> mostrarEmpleados(){
        return empleadoRepository.findAll();
    }

    public List<Empleado> mostrarEmpleadosByRoles(Roles roles){
        if(roles!=null){
            return empleadoRepository.findByRoles(roles);
        }
        return Collections.emptyList();
    }

    public EmpleadoResponseDto mapearADto(Empleado empleado) {
        EmpleadoResponseDto dto = new EmpleadoResponseDto();
        dto.setId(empleado.getId());
        dto.setNombreUsuario(empleado.getNombreUsuario());
        dto.setDni(empleado.getDni());
        dto.setNombre(empleado.getNombre());
        dto.setApellidos(empleado.getApellidos());
        dto.setEmail(empleado.getEmail());
        dto.setTelefono(empleado.getTelefono());
        dto.setRoles(empleado.getRoles());

        if (empleado.getEmpresa() != null) {
            dto.setEmpresaId(empleado.getEmpresa().getId());
        }

        return dto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado empleado = empleadoRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username));

        return new User(
                empleado.getNombreUsuario(),
                empleado.getPassword(),
                empleado.getRoles().stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.name()))
                        .collect(Collectors.toList())
        );
    }

}
