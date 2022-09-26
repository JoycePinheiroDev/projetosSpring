package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Enfermeiro;
import ifpb.edu.br.prontatendimento.model.Paciente;
import ifpb.edu.br.prontatendimento.model.Prontuario;
import ifpb.edu.br.prontatendimento.repository.EnfermeiroRepository;
import ifpb.edu.br.prontatendimento.repository.PacienteRepository;
import ifpb.edu.br.prontatendimento.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/prontuario")
@RestController
public class ProntuarioController {

    @Autowired
    private ProntuarioRepository _prontuarioRepository;

    @Autowired
    private PacienteRepository _pacienteRepository;

    @Autowired
    private EnfermeiroRepository _enfermeiroRepository;

    @GetMapping
    public List<Prontuario> get(){
        return _prontuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prontuario> getById(@PathVariable(value = "id") Integer id){
        Optional<Prontuario> prontuario = _prontuarioRepository.findById(id);
        if (prontuario.isPresent()){
            return new ResponseEntity<Prontuario>(prontuario.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Prontuario> post(@RequestBody Prontuario prontuario){
        Optional<Enfermeiro> enfermeiroOptional = _enfermeiroRepository.findById(prontuario.getEnfermeiro().getId());
        Optional<Paciente> pacienteOptional = _pacienteRepository.findById(prontuario.getPaciente().getId());
        if(enfermeiroOptional.isPresent() && pacienteOptional.isPresent()){
            Enfermeiro enfermeiro = enfermeiroOptional.get();
            prontuario.setEnfermeiro(enfermeiro);
            Paciente paciente = pacienteOptional.get();
            prontuario.setPaciente(paciente);
            return new ResponseEntity<Prontuario>(prontuario, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * TODO: Fazer o put e o delete*/

}
