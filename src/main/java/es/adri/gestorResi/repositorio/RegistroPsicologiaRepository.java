package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.RegistroPsicologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroPsicologiaRepository extends JpaRepository<RegistroPsicologia,Long> {

    Optional<RegistroPsicologia> findByFecha(LocalDate fecha);
    List<RegistroPsicologia> findAllByOrderByFechaDesc();
    List<RegistroPsicologia> findByEmpleadoId(Long empleadoId);
}
