package es.adri.gestorResi.repositorio;


import es.adri.gestorResi.entidades.enums.Roles;
import es.adri.gestorResi.entidades.personas.Residente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResidenteRepository extends JpaRepository<Residente, Long> {

    List<Residente> findAllByActivoTrue();
    Optional<Residente> findByDni(String dni);
    Optional<Residente> findById(Long id);
    List<Residente> findByHabitacion(String habitacion);

    @Modifying
    @Query(value = "UPDATE residente SET activo = false WHERE id = :id", nativeQuery = true)
    void softDeleteById(@Param("id") Long id);

}
