package br.com.Compras.Compras.Service;

import br.com.Compras.Compras.Entity.Produto;
import br.com.Compras.Compras.Repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Long id) throws IllegalAccessException {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()){
            return produtoRepository.findById(id);
        }else {
            throw new IllegalAccessException("Produto com ID: " + id + " não encontrado");
        }
    }

}
