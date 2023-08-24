package br.com.Compras.Compras.Repository;

import br.com.Compras.Compras.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
