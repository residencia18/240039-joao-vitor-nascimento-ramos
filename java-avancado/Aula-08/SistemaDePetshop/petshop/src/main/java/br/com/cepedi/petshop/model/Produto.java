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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PRODUTO")
@Getter
@NoArgsConstructor
@ToString
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    @NotNull
    @Column(name = "NOME" , nullable = false)
    private String nome;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_PRODUTO", nullable = false)
    @Setter
    private TipoProduto tipoProduto;

    @ManyToOne
    @JoinColumn(name = "ID_MARCA")
    @Setter
    private Marca marca;

    @Column(name = "DESCRIÇÃO")
    @Setter
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



    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        this.nome = nome;
    }

    public void setPreco(BigDecimal preco) throws PrecoInvalidoException {
        if (!Verificacoes.verificarPrecoMaiorQueZero(preco)) {
            throw new PrecoInvalidoException();
        }
        this.preco = preco;
    }

}
