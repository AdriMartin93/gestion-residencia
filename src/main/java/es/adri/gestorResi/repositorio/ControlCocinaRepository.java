package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.Caidas;
import es.adri.gestorResi.entidades.registros.ControlCocina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface ControlCocinaRepository extends JpaRepository<ControlCocina,Long> {

    List<ControlCocina> findAllByOrderByFechaDesc();

}
