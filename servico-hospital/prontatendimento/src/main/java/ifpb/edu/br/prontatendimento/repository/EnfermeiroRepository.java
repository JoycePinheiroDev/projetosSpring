package ifpb.edu.br.prontatendimento.repository;

import ifpb.edu.br.prontatendimento.model.Enfermeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfermeiroRepository extends JpaRepository<Enfermeiro, Integer> {

}
