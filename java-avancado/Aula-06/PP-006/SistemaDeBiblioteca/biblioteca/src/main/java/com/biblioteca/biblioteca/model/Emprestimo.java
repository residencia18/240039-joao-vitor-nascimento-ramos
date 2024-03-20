package com.biblioteca.biblioteca.model;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="EMPRESTIMO")
@Getter
@NoArgsConstructor
@ToString
public class Emprestimo {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "LIVRO_ID", nullable = false)
    private Livro livro;
    
    @Column(name="DATA_EMPRESTIMO")
    @Setter
    private LocalDateTime data_emprestimo;

    @Column(name="DATA_DEVOLUCAO")
    @Nullable
    @Setter
    private LocalDateTime data_devolucao;


    
    public Emprestimo(Emprestimo emprestimo) {
        this.id = emprestimo.getId();
        this.cliente = emprestimo.getCliente();
        this.livro = emprestimo.getLivro();
        this.data_emprestimo = emprestimo.getData_emprestimo();
        this.data_devolucao = emprestimo.getData_devolucao();
    }




    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente é obrigatório");
        }
        this.cliente = cliente;
    }


    public void setLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro é obrigatório");
        }
        this.livro = livro;
    }




    
    
}
