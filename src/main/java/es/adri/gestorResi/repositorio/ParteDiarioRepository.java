package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.ParteDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParteDiarioRepository extends JpaRepository<ParteDiario, Long> {

    Optional<ParteDiario> findByFecha(LocalDate fecha);
    List<ParteDiario> findAllByOrderByFechaDesc();
    List<ParteDiario> findByEmpleadoId(Long empleadoId);

}
