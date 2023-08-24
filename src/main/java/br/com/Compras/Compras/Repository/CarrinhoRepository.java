package br.com.Compras.Compras.Repository;

import br.com.Compras.Compras.Entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
