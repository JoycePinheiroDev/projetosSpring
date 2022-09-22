package ifpb.edu.br.prontatendimento.repository;

import ifpb.edu.br.prontatendimento.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

}
