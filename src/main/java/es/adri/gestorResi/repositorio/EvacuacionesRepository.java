package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.diariosRes.Evacuaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface EvacuacionesRepository extends JpaRepository<Evacuaciones,Long> {

    List<Evacuaciones> findByResidenteIdOrderByFechaHoraDesc(Long residenteId);
    List<Evacuaciones> findByResidenteIdAndFechaHoraBetweenOrderByFechaHoraDesc(Long ResidenteId, LocalDateTime inicio, LocalDateTime fin);
    List<Evacuaciones> findByFechaHoraBetweenOrderByFechaHoraDesc(LocalDateTime inicio, LocalDateTime fin);
}
