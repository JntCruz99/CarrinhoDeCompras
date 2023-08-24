package br.com.Compras.Compras.Entity;

import br.com.Compras.Compras.Entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_carrinho")
public class Carrinho implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double total;

    private Status status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name="produto_no_carrinho", joinColumns=
            {@JoinColumn(name="carrinho_id")}, inverseJoinColumns=
            {@JoinColumn(name="produto_id")})
    private List<Produto> produtos;

    public Carrinho() {
    }

    public Carrinho(Status status, Cliente cliente, List<Produto> produtos) {
        this.status = status;
        this.cliente = cliente;
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        Double total = 0.0;

        for (Produto produto : produtos) {
            total += produto.getPreco();
        }

        return total;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
