package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.RegistroAnimacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroAnimacionRepository extends JpaRepository<RegistroAnimacion, Long> {

    Optional<RegistroAnimacion> findByFechaHora(LocalDateTime fechaHora);
    List<RegistroAnimacion> findAllByOrderByFechaHoraDesc();
    List<RegistroAnimacion> findByEmpleadoId(Long empleadoId);
}
