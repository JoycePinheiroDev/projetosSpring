package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Usuario;
import ifpb.edu.br.prontatendimento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository _usuarioRepository;

    @GetMapping
    public List<Usuario> get(){
        return _usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable(value = "id") Integer id){
        Optional<Usuario> usuario = _usuarioRepository.findById(id);
        if(usuario.isPresent())
            return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Usuario post(@RequestBody Usuario usuario){
        return _usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> put(@PathVariable(value = "id") Integer id, @RequestBody Usuario newUsuario)
    {
        Optional<Usuario> oldUsuario = _usuarioRepository.findById(id);
        if(oldUsuario.isPresent()){
            Usuario usuario = oldUsuario.get();
            usuario.setNome(newUsuario.getNome());
            usuario.setEndereco(newUsuario.getEndereco());
            usuario.setData_nascimento(newUsuario.getData_nascimento());
            usuario.setTelefone(newUsuario.getTelefone());
            _usuarioRepository.save(usuario);
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Usuario> usuario = _usuarioRepository.findById(id);
        if(usuario.isPresent()){
            _usuarioRepository.delete(usuario.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}



