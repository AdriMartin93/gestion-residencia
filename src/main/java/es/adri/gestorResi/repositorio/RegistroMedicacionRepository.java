package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.diariosRes.RegistroMedicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroMedicacionRepository extends JpaRepository<RegistroMedicacion, Long> {

    List<RegistroMedicacion> findByResidenteId(Long residenteId);
    List<RegistroMedicacion> findByFechaHoraRealBetween(LocalDateTime inicio, LocalDateTime fin);
    List<RegistroMedicacion> findByResidenteIdAndFechaHoraBetween(Long residenteId, LocalDateTime inicio, LocalDateTime fin);
}
