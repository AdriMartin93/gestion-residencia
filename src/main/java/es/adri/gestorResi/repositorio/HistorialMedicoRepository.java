package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.salud.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Long> {

    Optional<HistorialMedico> findByResidenteId(long residenteId);
    List<HistorialMedico> findAllByOrderByResidenteNombreAsc();
}
