package ifpb.edu.br.prontatendimento.repository;

import ifpb.edu.br.prontatendimento.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Integer> {

}
