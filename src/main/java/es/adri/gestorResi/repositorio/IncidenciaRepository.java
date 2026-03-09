package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia,Long> {

    Optional<Incidencia> findById(Long id);
    List<Incidencia> findAllByOrderByFechaHoraDesc(LocalDateTime fechaHora);
    List<Incidencia> findByResidenteIdOrderByFechaHoraDesc(Long id);

}
