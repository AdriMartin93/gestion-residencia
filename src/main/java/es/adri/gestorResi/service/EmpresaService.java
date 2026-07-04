package es.adri.gestorResi.service;


import es.adri.gestorResi.entidades.Dtos.RegistroEmpresaDto;
import es.adri.gestorResi.entidades.enums.Roles;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Empresa;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.EmpresaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpresaRepository empresaRepository;
    private final PasswordEncoder passwordEncoder;

    public Empresa findById(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con el ID: " + id));
    }

    @Transactional
    public void registrarEmpresaYAdministrador(RegistroEmpresaDto dto) {

        Empresa empresa = dto.getEmpresa();
        Empleado admin = dto.getAdministrador();

        if (empresaRepository.existsByCif(empresa.getCif())) {
            throw new RuntimeException("El CIF ya está registrado en el sistema.");
        }
        if (empresaRepository.existsByEmail(empresa.getEmail())) {
            throw new RuntimeException("El email de la empresa ya está en uso.");
        }

        if(empleadoRepository.existsByNombreUsuario(admin.getNombreUsuario())){
            throw new RuntimeException("El nombre de usuario administrador ya existe.");
        }

        if (empleadoRepository.existsByDni(admin.getDni())) {
            throw new RuntimeException("El DNI del administrador ya existe.");
        }

        if (empleadoRepository.existsByEmail(admin.getEmail())) {
            throw new RuntimeException("El email del administrador ya existe.");
        }

        Empresa empresaGuardada = empresaRepository.save(empresa);
        admin.setEmpresa(empresaGuardada);
        if (admin.getRoles() == null || admin.getRoles().isEmpty()) {
            admin.setRoles(Set.of(Roles.ROLE_DIRECTOR));
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        empleadoRepository.save(admin);
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }
}
