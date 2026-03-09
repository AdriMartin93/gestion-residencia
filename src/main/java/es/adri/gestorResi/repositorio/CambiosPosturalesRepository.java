package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.diariosRes.CambioPostural;
import es.adri.gestorResi.entidades.registros.diariosRes.Higiene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CambiosPosturalesRepository extends JpaRepository<CambioPostural,Long> {

    List<CambioPostural> findByFechaHoraBetweenOrderByFechaHoraDesc(LocalDateTime inicio, LocalDateTime fin);
    List<CambioPostural> findByResidenteIdOrderByFechaHoraDesc(Long residenteId);
    List<CambioPostural> findByResidenteIdAndFechaHoraBetweenOrderByFechaHoraDesc(Long residenteId, LocalDateTime inicio, LocalDateTime fin);
    Optional<CambioPostural> findFirstByResidenteIdOrderByFechaHoraDesc(Long residenteId);
    List<CambioPostural> findAllByOrderByFechaHoraDesc();
}
