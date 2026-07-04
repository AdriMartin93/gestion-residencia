package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.salud.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Long> {

    Optional<HistorialMedico> findById(Long id);

    @Query("SELECT r.historialMedico FROM Residente r WHERE r.id = :residenteId")
    Optional<HistorialMedico> findByResidenteId(@Param("residenteId") Long residenteId);

    @Query("SELECT h FROM Residente r JOIN r.historialMedico h ORDER BY r.nombre ASC")
    List<HistorialMedico> findAllByOrderByResidenteNombreAsc();
}
