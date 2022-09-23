package ifpb.edu.br.prontatendimento.repository;

import ifpb.edu.br.prontatendimento.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
