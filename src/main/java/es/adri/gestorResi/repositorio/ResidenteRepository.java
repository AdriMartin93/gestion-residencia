package es.adri.gestorResi.repositorio;


import es.adri.gestorResi.entidades.enums.Roles;
import es.adri.gestorResi.entidades.personas.Residente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResidenteRepository extends JpaRepository<Residente, Integer> {

    Optional<Residente> findByDni(String dni);
    Optional<Residente> findByResidenteId(Long id);
    List<Residente> findByNombre(String nombre);
    List<Residente> findByHabitacion(String habitacion);

}
