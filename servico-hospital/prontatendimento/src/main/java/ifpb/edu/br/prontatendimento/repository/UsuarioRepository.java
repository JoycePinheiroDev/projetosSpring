package ifpb.edu.br.prontatendimento.repository;

import ifpb.edu.br.prontatendimento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
