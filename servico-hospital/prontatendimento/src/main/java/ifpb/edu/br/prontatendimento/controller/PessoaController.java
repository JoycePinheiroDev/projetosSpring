package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Pessoa;
import ifpb.edu.br.prontatendimento.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository _pessoaRepository;

    @GetMapping
    public List<Pessoa> get(){
        return _pessoaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getById(@PathVariable(value = "id") Integer id){
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if(pessoa.isPresent())
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * TODO: Criar a verificação se já existe o usuário na base
     * */
    @PostMapping
    public Pessoa post(@RequestBody Pessoa pessoa){
        return _pessoaRepository.save(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> put(@PathVariable(value = "id") Integer id, @RequestBody Pessoa newPessoa)
    {
        Optional<Pessoa> oldPessoa = _pessoaRepository.findById(id);
        if(oldPessoa.isPresent()){
            Pessoa pessoa = oldPessoa.get();
            pessoa.setNome(newPessoa.getNome());
            pessoa.setEndereco(newPessoa.getEndereco());
            pessoa.setData_nascimento(newPessoa.getData_nascimento());
            pessoa.setTelefone(newPessoa.getTelefone());
            _pessoaRepository.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if(pessoa.isPresent()){
            _pessoaRepository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}



