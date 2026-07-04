package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.limpieza.LimpiezaBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LimpiezaBaseRepository extends JpaRepository<LimpiezaBase, Long> {


    List<LimpiezaBase> findByEmpleadoId(Long empleadoId);


    List<LimpiezaBase> findByFecha(LocalDate fecha);


    List<LimpiezaBase> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}