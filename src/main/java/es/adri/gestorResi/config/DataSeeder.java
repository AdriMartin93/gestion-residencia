package es.adri.gestorResi.config;


import es.adri.gestorResi.entidades.enums.Roles;
import es.adri.gestorResi.entidades.personas.Empleado;
import es.adri.gestorResi.entidades.personas.Empresa;
import es.adri.gestorResi.repositorio.EmpleadoRepository;
import es.adri.gestorResi.repositorio.EmpresaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    private final EmpresaRepository empresaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(EmpresaRepository empresaRepository,
                      EmpleadoRepository empleadoRepository,
                      PasswordEncoder passwordEncoder) {
        this.empresaRepository = empresaRepository;
        this.empleadoRepository = empleadoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>> INICIANDO EJECUCIÓN DE DATA SEEDER <<<<<<<<<<");

        String usuarioDemo = "adminDemo";
        String cifDemo = "B12345678";

        try {
            if (!empleadoRepository.existsByNombreUsuario(usuarioDemo)) {
                System.out.println("⏳ Usuario demo no encontrado, creando registros...");

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

                System.out.println("=================================================");
                System.out.println("✅ Usuario demo creado con éxito en PostgreSQL.");
                System.out.println("👤 Usuario: " + usuarioDemo);
                System.out.println("🔑 Password: admin123");
                System.out.println("=================================================");
            } else {
                System.out.println("ℹ️ El usuario adminDemo ya existe en la base de datos.");
            }
        } catch (Exception e) {
            System.err.println("❌ ERROR AL EJECUTAR EL SEEDER: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
