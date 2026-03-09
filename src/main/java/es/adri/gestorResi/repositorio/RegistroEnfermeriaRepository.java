package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.RegistroEnfermeria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroEnfermeriaRepository extends JpaRepository<RegistroEnfermeria, Long> {

    Optional<RegistroEnfermeria> findByFecha(LocalDate fecha);
    List<RegistroEnfermeria> findAllByOrderByFechaDesc();
    List<RegistroEnfermeria> findByEmpleadoId(Long empleadoId);
}
