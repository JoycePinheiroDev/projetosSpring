package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Enfermeiro;
import ifpb.edu.br.prontatendimento.model.Medico;
import ifpb.edu.br.prontatendimento.model.Usuario;
import ifpb.edu.br.prontatendimento.repository.MedicoRepository;
import ifpb.edu.br.prontatendimento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository _medicoRepository;

    @Autowired
    private UsuarioRepository _usuarioRepository;

    @GetMapping
    public List<Medico> get(){
        return _medicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> getById(@PathVariable(value = "id") Integer id){
        Optional<Medico> medico = _medicoRepository.findById(id);
        if (medico.isPresent()){
            return new ResponseEntity<Medico>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Medico> post(@RequestBody Medico medico){
        Optional<Usuario> usuarioOptional = _usuarioRepository.findById(medico.getUsuario().getId());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            medico.setUsuario(usuario);
            _medicoRepository.save(medico);
            return new ResponseEntity<Medico>(medico, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> put(@PathVariable(value = "id") Integer id, @RequestBody Medico newMedico) {
        Optional<Medico> oldMedico = _medicoRepository.findById(id);
        if (oldMedico.isPresent()) {
            Medico medico = oldMedico.get();
            medico.setCrm(newMedico.getCrm());
            medico.setEspecialidade(newMedico.getEspecialidade());
            _medicoRepository.save(medico);
            return new ResponseEntity<Medico>(medico, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")  Integer  id){
        Optional<Medico> medico = _medicoRepository.findById(id);
        if (medico.isPresent()){
            _medicoRepository.delete(medico.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
