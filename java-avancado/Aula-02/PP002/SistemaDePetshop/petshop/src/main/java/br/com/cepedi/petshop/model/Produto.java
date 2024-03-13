package br.com.cepedi.petshop.model;

import java.math.BigDecimal;

import org.antlr.v4.runtime.misc.NotNull;

import br.com.cepedi.petshop.exceptions.PrecoInvalidoException;
import br.com.cepedi.petshop.verificacoes.Verificacoes;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "NOME" , nullable = false)
    private String nome;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_PRODUTO", nullable = false)
    private TipoProduto tipoProduto;

    @ManyToOne
    @JoinColumn(name = "ID_MARCA")
    private Marca marca;

    @Column(name = "DESCRIÇÃO")
    private String descricao;

    @NotNull
    @Column(name = "PRECO", nullable = false)
    private BigDecimal preco;

    public Produto(String nome, TipoProduto tipoProduto, Marca marca, String descricao, BigDecimal preco) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.marca = marca;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto() {
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
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        this.nome = nome;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
    	
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) throws PrecoInvalidoException {
        if (!Verificacoes.verificarPrecoMaiorQueZero(preco)) {
            throw new PrecoInvalidoException();
        }
        this.preco = preco;
    }

}
