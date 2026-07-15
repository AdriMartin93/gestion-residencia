package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.registros.Fichaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FichajeRepository extends JpaRepository<Fichaje, Long> {


    Optional<Fichaje> findFirstByEmpleadoNombreUsuarioOrderByEntradaDesc(String nombreUsuario);


    List<Fichaje> findAllByOrderByEntradaDesc();
}