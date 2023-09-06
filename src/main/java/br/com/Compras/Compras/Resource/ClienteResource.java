package br.com.Compras.Compras.Resource;

import br.com.Compras.Compras.Entity.Cliente;
import br.com.Compras.Compras.Entity.enums.Status;
import br.com.Compras.Compras.Repository.ClienteRepository;
import br.com.Compras.Compras.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientes")
@CrossOrigin(origins = "*")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarCliente(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClienteId(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
        }catch (RuntimeException | IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionarCliente(@RequestBody Cliente cliente){
        try {
            clienteService.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        }catch (RuntimeException | IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        try {
            clienteService.update(id, cliente);
            return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(cliente);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id){
        try {
            Optional<Cliente> cliente =  clienteService.findById(id);
            clienteService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Cliente" + cliente.get().getNome() + "Deletado com sucesso");
        }catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        }
    }
    @GetMapping("/pendente")
    public ResponseEntity<?> status(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findByCarrinhos_Status(Status.PENDENTE));
    }

    @GetMapping("/{nomeCliente}")
    public ResponseEntity<?> procurarNome(@PathVariable String nome){
        Optional<Cliente> cliente = clienteService.ByCliente(nome);
        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }

}
