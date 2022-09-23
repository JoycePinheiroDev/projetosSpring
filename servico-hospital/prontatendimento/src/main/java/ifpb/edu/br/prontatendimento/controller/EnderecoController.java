package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Endereco;
import ifpb.edu.br.prontatendimento.model.Pessoa;
import ifpb.edu.br.prontatendimento.repository.EnderecoRepository;
import ifpb.edu.br.prontatendimento.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository _enderecoRepository;

    @Autowired
    private PessoaRepository _pessoaRepository;

    @GetMapping
    public List<Endereco> get(){
        return _enderecoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getById(@PathVariable(value = "id") Integer id){
        Optional<Endereco> endereco = _enderecoRepository.findById(id);
        if (endereco.isPresent()){
            return new ResponseEntity<Endereco>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Endereco> post(@RequestBody Endereco endereco){
        Optional<Pessoa> pessoaOptional = _pessoaRepository.findById(endereco.getPessoa().getId());
        if (pessoaOptional.isPresent()){
            Pessoa pessoa = pessoaOptional.get();
            endereco.setPessoa(pessoa);
            _enderecoRepository.save(endereco);
            return new ResponseEntity<Endereco>(endereco, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> put(@PathVariable(value = "id") Integer id, @RequestBody Endereco newEndereco){
        Optional<Endereco> oldEndereco = _enderecoRepository.findById(id);
        if (oldEndereco.isPresent()){
            Endereco endereco = oldEndereco.get();
            endereco.setRua(newEndereco.getRua());
            endereco.setNumero(newEndereco.getNumero());
            endereco.setBairro(newEndereco.getBairro());
            endereco.setCidade(newEndereco.getCidade());
            endereco.setEstado(newEndereco.getEstado());
            _enderecoRepository.save(endereco);
            return new ResponseEntity<Endereco>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id){
        Optional<Endereco> endereco = _enderecoRepository.findById(id);
        if (endereco.isPresent()){
            _enderecoRepository.delete(endereco.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}







