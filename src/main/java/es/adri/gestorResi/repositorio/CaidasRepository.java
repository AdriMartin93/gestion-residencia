package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.Caidas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CaidasRepository extends JpaRepository <Caidas, Long>{

    List<Caidas> findByResidenteIdOrderByFechaHoraDesc(Long id);

    List<Caidas> findAllByOrderByFechaHoraDesc();
}
