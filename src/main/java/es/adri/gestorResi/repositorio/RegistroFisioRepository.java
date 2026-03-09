package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.enums.ActividadFisio;
import es.adri.gestorResi.entidades.registros.RegistroFisio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroFisioRepository extends JpaRepository<RegistroFisio,Long> {

    Optional<RegistroFisio> findByFechaRegistro(LocalDate fecha);
    List<RegistroFisio> findByEmpleadoId(Long empleadoId);
    List<RegistroFisio> findByActividadFisio(ActividadFisio actividadFisio);
    List<RegistroFisio> findByResidentesId(Long residenteId);
}
