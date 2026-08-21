package es.adri.gestorResi;


import es.adri.gestorResi.entidades.enums.Roles;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Empresa;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.EmpresaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(
            EmpresaRepository empresaRepository,
            EmpleadoRepository empleadoRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            String usuarioDemo = "adminDemo";
            String cifDemo = "B12345678";

            if (!empleadoRepository.existsByNombreUsuario(usuarioDemo)) {

                Empresa empresaDemo = empresaRepository.findAll().stream()
                        .filter(e -> cifDemo.equals(e.getCif()))
                        .findFirst()
                        .orElseGet(() -> {
                            Empresa nueva = new Empresa();
                            nueva.setNombreComercial("Residencia Los Olivos (Demo)");
                            nueva.setCif(cifDemo);
                            nueva.setEmail("contacto@losolivosdemo.com");
                            return empresaRepository.save(nueva);
                        });


                Empleado adminDemo = new Empleado();
                adminDemo.setNombreUsuario(usuarioDemo);
                adminDemo.setDni("12345678Z");
                adminDemo.setNombre("Administrador");
                adminDemo.setApellidos("Principal Demo");
                adminDemo.setEmail("admin@losolivosdemo.com");
                adminDemo.setTelefono("600123456");
                adminDemo.setPassword(passwordEncoder.encode("admin123"));
                adminDemo.setRoles(Set.of(Roles.ROLE_DIRECTOR));
                adminDemo.setEmpresa(empresaDemo);

                empleadoRepository.save(adminDemo);

                System.out.println("✅ Usuario demo creado/actualizado con éxito en PostgreSQL.");
            }
        };
    }
}
