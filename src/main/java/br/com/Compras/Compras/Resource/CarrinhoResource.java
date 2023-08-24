package br.com.Compras.Compras.Resource;

import br.com.Compras.Compras.Entity.Carrinho;
import br.com.Compras.Compras.Entity.enums.Status;
import br.com.Compras.Compras.Repository.CarrinhoRepository;
import br.com.Compras.Compras.Service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/carrinhos")
@CrossOrigin(origins = "*")
public class CarrinhoResource {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @GetMapping
    public ResponseEntity<?> listarCarrinhos(){
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCarrinho(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.findById(id));
        }catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro:" + e.getMessage());
        }
    }

    @GetMapping("/idcliente/{idCliente}/idproduto/{idProduto}")
    public ResponseEntity<?> criarCarrinho(@PathVariable Long idCliente, @PathVariable Long idProduto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoService.save(idProduto,idCliente));
        }catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        }
    }

    @GetMapping("/pendente")
    public ResponseEntity<?> seila(){
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoRepository.findByStatus(Status.PENDENTE));
    }

}
