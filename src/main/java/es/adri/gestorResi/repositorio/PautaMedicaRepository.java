package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.salud.PautaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PautaMedicaRepository extends JpaRepository<PautaMedica, Long> {

    List<PautaMedica> findByResidenteId(long residenteId);
}
