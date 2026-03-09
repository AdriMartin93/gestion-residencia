package es.adri.gestorResi.service;


import es.adri.gestorResi.entidades.enums.Roles;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    private PasswordEncoder passwordEncoder;

    public Empleado findById(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Transactional
    public void registrarEmpleado(Empleado empleado) {
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

}
