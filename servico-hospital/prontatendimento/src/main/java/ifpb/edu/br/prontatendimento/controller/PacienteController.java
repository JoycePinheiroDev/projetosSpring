package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Paciente;
import ifpb.edu.br.prontatendimento.model.Usuario;
import ifpb.edu.br.prontatendimento.repository.PacienteRepository;
import ifpb.edu.br.prontatendimento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteRepository _pacienteRepository;

    @Autowired
    private UsuarioRepository _usuarioRepository;

    @GetMapping
    public List<Paciente> get(){
        return _pacienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable(value = "id") Integer id){
        Optional<Paciente> paciente = _pacienteRepository.findById(id);
        if(paciente.isPresent()){
            return new ResponseEntity<Paciente>(paciente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> post(@RequestBody Paciente paciente){
        Optional<Usuario> usuarioOptional = _usuarioRepository.findById(paciente.getUsuario().getId());
        if (usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            paciente.setUsuario(usuario);
            _pacienteRepository.save(paciente);
            return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> put(@PathVariable(value = "id") Integer id, @RequestBody Paciente newPaciente){
        Optional<Paciente> oldPaciente = _pacienteRepository.findById(id);
        if(oldPaciente.isPresent()){
            Paciente paciente = oldPaciente.get();
            paciente.setNumero_sus(newPaciente.getNumero_sus());
            _pacienteRepository.save(paciente);
            return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id){
        Optional<Paciente> paciente = _pacienteRepository.findById(id);
        if (paciente.isPresent()){
            _pacienteRepository.delete(paciente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
