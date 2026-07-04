package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.enums.Roles;
import es.adri.gestorResi.entidades.personas.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
    Optional<Empleado> findById(Long id);
    List<Empleado> findByRoles(Roles roles);
    List<Empleado> findByEmpresaId(Long empresaId);
    Optional<Empleado> findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
    long countByEmpresaIdAndRolesContaining(Long empresaId, Roles rol);

}
