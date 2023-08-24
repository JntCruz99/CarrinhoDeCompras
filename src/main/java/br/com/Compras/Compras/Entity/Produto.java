package br.com.Compras.Compras.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String disc;

    private Double preco;

    @JsonIgnore
    @ManyToMany(mappedBy="produtos")
    private List<Carrinho> carrinho;

    public Produto() {
    }

    public Produto(String nome, String disc, Double preco, List<Carrinho> carrinho) {
        this.nome = nome;
        this.disc = disc;
        this.preco = preco;
        this.carrinho = carrinho;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Carrinho> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Carrinho> carrinho) {
        this.carrinho = carrinho;
    }
}
