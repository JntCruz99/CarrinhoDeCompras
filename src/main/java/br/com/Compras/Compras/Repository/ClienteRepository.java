package br.com.Compras.Compras.Repository;

import br.com.Compras.Compras.Entity.Cliente;
import br.com.Compras.Compras.Entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String cpf);

    List<Cliente> findByCarrinhos_Status(Status status);;
}
