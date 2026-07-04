package es.adri.gestorResi.repositorio;

import es.adri.gestorResi.entidades.personas.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    boolean existsByCif(String cif);
    boolean existsByEmail(String email);

}
