package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Enfermeiro;
import ifpb.edu.br.prontatendimento.model.Usuario;
import ifpb.edu.br.prontatendimento.repository.EnfermeiroRepository;
import ifpb.edu.br.prontatendimento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enfermeiro")
public class EnfermeiroController {

    @Autowired
    private EnfermeiroRepository _enfermeiroRepository;

    @Autowired
    private UsuarioRepository _usuarioRepository;

    @GetMapping
    public List<Enfermeiro> get(){
        return _enfermeiroRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enfermeiro> getById(@PathVariable(value = "id") Integer id){
        Optional<Enfermeiro> enfermeiro = _enfermeiroRepository.findById(id);
        if (enfermeiro.isPresent()){
            return new ResponseEntity<Enfermeiro>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Enfermeiro> post(@RequestBody Enfermeiro enfermeiro) {
        Optional<Usuario> usuarioOptional = _usuarioRepository.findById(enfermeiro.getUsuario().getId());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            enfermeiro.setUsuario(usuario);
            _enfermeiroRepository.save(enfermeiro);
            return new ResponseEntity<Enfermeiro>(enfermeiro, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enfermeiro> put(@PathVariable(value = "id") Integer id, @RequestBody Enfermeiro newEnfermeiro){
        Optional<Enfermeiro> oldEnfermeiro = _enfermeiroRepository.findById(id);
        if (oldEnfermeiro.isPresent()){
            Enfermeiro enfermeiro = oldEnfermeiro.get();
            enfermeiro.setCoren(newEnfermeiro.getCoren());
            _enfermeiroRepository.save(enfermeiro);
            return new ResponseEntity<Enfermeiro>(enfermeiro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")  Integer  id){
        Optional<Enfermeiro> enfermeiro = _enfermeiroRepository.findById(id);
        if (enfermeiro.isPresent()){
            _enfermeiroRepository.delete(enfermeiro.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
