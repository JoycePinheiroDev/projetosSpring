package ifpb.edu.br.prontatendimento.repository;

import ifpb.edu.br.prontatendimento.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
