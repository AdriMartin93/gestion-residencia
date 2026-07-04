package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.RegistroEnfermeria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroEnfermeriaRepository extends JpaRepository<RegistroEnfermeria, Long> {

    Optional<RegistroEnfermeria> findByFechaHora(LocalDateTime fechaHora);
    List<RegistroEnfermeria> findAllByOrderByFechaHoraDesc();
    List<RegistroEnfermeria> findByEnfermero_Id(Long empleadoId);
    List<RegistroEnfermeria> findByResidenteIdOrderByFechaHoraDesc(Long residenteId);

}
