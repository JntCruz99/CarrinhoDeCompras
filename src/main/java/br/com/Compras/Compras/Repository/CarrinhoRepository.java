package br.com.Compras.Compras.Repository;

import br.com.Compras.Compras.Entity.Carrinho;
import br.com.Compras.Compras.Entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    List<Carrinho> findByStatus(Status status);

}
