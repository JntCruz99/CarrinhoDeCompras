package br.com.Compras.Compras.Service;

import br.com.Compras.Compras.Entity.Carrinho;
import br.com.Compras.Compras.Entity.Cliente;
import br.com.Compras.Compras.Entity.Produto;
import br.com.Compras.Compras.Entity.enums.Status;
import br.com.Compras.Compras.Repository.CarrinhoRepository;
import br.com.Compras.Compras.Repository.ClienteRepository;
import br.com.Compras.Compras.Repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Object save(Long idProduto, Long idCliente) throws IllegalAccessException {
        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        List<Produto> listaproduto = new ArrayList<>();
        listaproduto.add(produtoOptional.get());
        if(produtoOptional.isPresent() && clienteOptional.isPresent()){
            Carrinho carrinho = new Carrinho(Status.PENDENTE, clienteOptional.get(),
                    listaproduto);
             return carrinhoRepository.save(carrinho);
        }else if (produtoOptional.isPresent()){
             throw new IllegalAccessException("O cliente de ID: " + idCliente + " não foi encontrado");
        }else {
            throw new IllegalAccessException("O Produto de ID: " + idProduto + " não foi encontrado");
        }
    }

    @Transactional
    public void delete(Long id) throws IllegalAccessException {
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(id);
        if (carrinhoOptional.isPresent()){
            carrinhoRepository.delete(carrinhoOptional.get());
        }else {
            throw new IllegalAccessException("Erro ao Exluir carrinho de ID: " + id);
        }
    }

    public List<Carrinho> findAll(){
        return carrinhoRepository.findAll();
    }

    public Optional<Carrinho> findById(Long id) throws IllegalAccessException {
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(id);
        if (carrinhoOptional.isPresent()){
            return carrinhoRepository.findById(id);
        }else {
            throw new IllegalAccessException("Carrinho de ID: " + id + "Não localizado");
        }
    }



}
