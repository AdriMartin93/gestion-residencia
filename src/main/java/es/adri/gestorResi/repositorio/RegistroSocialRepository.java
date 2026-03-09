package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.enums.trabajoSocial.EstadoTramite;
import es.adri.gestorResi.entidades.registros.RegistroSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroSocialRepository extends JpaRepository<RegistroSocial, Long> {

    Optional<RegistroSocial> findByFechaRegistro(LocalDate fecha);
    List<RegistroSocial> findAllByOrderFechaRegistroDesc();
    List<RegistroSocial> findByTrabajadorSocialId(Long empleadoId);
    List<RegistroSocial> findByEstado(EstadoTramite estado);
}
