package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.diariosRes.Higiene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface HigieneRepository extends JpaRepository<Higiene, Long> {

    List<Higiene> findByFechaHoraBetweenOrderByFechaHoraDesc(LocalDateTime inicio,LocalDateTime fin);
    List<Higiene> findByResidenteIdOrderByFechaHoraDesc(Long residenteId);
    List<Higiene> findByResidenteIdAndFechaHoraBetweenOrderByFechaHoraDesc(Long residenteId, LocalDateTime inicio, LocalDateTime fin);
}
