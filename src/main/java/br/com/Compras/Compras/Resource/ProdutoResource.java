package br.com.Compras.Compras.Resource;

import br.com.Compras.Compras.Entity.Produto;
import br.com.Compras.Compras.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produtos")
@CrossOrigin(origins = "*")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<Produto>> listarProdutos(@PageableDefault(page = 0, size = 6)Pageable pageable) {
        Page<Produto> produtos = produtoService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProdutos(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.findById(id));
        }catch (IllegalAccessException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionarProdutos(@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produto));
    }

}
