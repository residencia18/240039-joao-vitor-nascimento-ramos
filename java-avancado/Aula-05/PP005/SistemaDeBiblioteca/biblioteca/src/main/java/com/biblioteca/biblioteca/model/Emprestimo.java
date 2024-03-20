package com.biblioteca.biblioteca.model;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name="EMPRESTIMO")
public class Emprestimo {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "LIVRO_ID", nullable = false)
    private Livro livro;
    
    @Column(name="DATA_EMPRESTIMO")
    @NotNull
    private LocalDateTime data_emprestimo;

    @Column(name="DATA_DEVOLUCAO")
    @Nullable
    private LocalDateTime data_devolucao;

    public Emprestimo() {
        super();
    }
    
    public Emprestimo(Emprestimo emprestimo) {
        this.id = emprestimo.getId();
        this.cliente = emprestimo.getCliente();
        this.livro = emprestimo.getLivro();
        this.data_emprestimo = emprestimo.getData_emprestimo();
        this.data_devolucao = emprestimo.getData_devolucao();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente é obrigatório");
        }
        this.cliente = cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro é obrigatório");
        }
        this.livro = livro;
    }

    public LocalDateTime getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(LocalDateTime data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public LocalDateTime getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(LocalDateTime data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

	@Override
	public String toString() {
		return "Emprestimo [id=" + id + ", cliente=" + cliente + ", livro=" + livro + ", data_emprestimo="
				+ data_emprestimo + ", data_devolucao=" + data_devolucao + "]";
	}
    
    
    
}
