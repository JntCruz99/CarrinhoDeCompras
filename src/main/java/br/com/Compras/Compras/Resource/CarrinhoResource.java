package br.com.Compras.Compras.Resource;

import br.com.Compras.Compras.Entity.Carrinho;
import br.com.Compras.Compras.Entity.Cliente;
import br.com.Compras.Compras.Entity.Produto;
import br.com.Compras.Compras.Entity.enums.Status;
import br.com.Compras.Compras.Repository.CarrinhoRepository;
import br.com.Compras.Compras.Repository.ProdutoRepository;
import br.com.Compras.Compras.Service.CarrinhoService;
import br.com.Compras.Compras.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/carrinhos")
@CrossOrigin(origins = "*")
public class CarrinhoResource {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoRepository produtoRepository;

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
    @PostMapping("/idcliente/{idCliente}/idproduto/{idProduto}")
    public ResponseEntity<?> criarCarrinho(@PathVariable Long idCliente, @PathVariable Long idProduto) throws IllegalAccessException {
        try {
            Optional<Cliente> clienteOptional = clienteService.findById(idCliente);
            List<Carrinho> carrinho = clienteService.listaDeCarrinhos(idCliente, Status.PENDENTE);
            Carrinho carrinhoSalvar = null;
            if (clienteOptional.isPresent() && carrinho.size() == 1) {
                    Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
                    if (produtoOptional.isPresent()) {
                        for (Carrinho c : carrinho) {
                            if (c.getStatus().equals(Status.PENDENTE)) {
                                List<Produto> produtosqJaestao = c.getProdutos();
                                produtosqJaestao.add(produtoOptional.get());
                                c.setProdutos(produtosqJaestao);
                                carrinhoSalvar = c;
                                break;
                            }}}
                return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoRepository.save(carrinhoSalvar));
            }else {
                return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoService.save(idProduto, idCliente));
            }
        }catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        }

    }
    @GetMapping("/pendente/{idCliente}")
    public List<Carrinho> seila(@PathVariable Long idCliente) throws IllegalAccessException {

        return clienteService.listaDeCarrinhos(idCliente, Status.PENDENTE);
    }

}
