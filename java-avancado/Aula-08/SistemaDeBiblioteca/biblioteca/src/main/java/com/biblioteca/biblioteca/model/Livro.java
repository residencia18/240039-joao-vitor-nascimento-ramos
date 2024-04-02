package com.biblioteca.biblioteca.model;

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
@Table(name="LIVRO")
@Getter
@NoArgsConstructor
@ToString
public class Livro {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter
    private Long id;
    
    @Column(name="NOME" , nullable = false)
    private String nome;
    
    @ManyToOne
    @JoinColumn(name="AUTOR_ID" , nullable = false)
    private Autor autor;
    
    @ManyToOne
    @JoinColumn(name="EDITORA_ID" , nullable = false)
    private Editora editora;
    

    
    public Livro(Livro livro) {
        this.id = livro.getId();
        this.nome = livro.getNome();
        this.autor = livro.getAutor();
        this.editora = livro.getEditora();
    }
    

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        
        this.nome = nome;
    }
    

    public void setAutor(Autor autor) {
        if (autor == null) {
            throw new IllegalArgumentException("Autor é obrigatório");
        }
        this.autor = autor;
    }


    public void setEditora(Editora editora) {
        if (editora == null) {
            throw new IllegalArgumentException("Editora é obrigatória");
        }
        this.editora = editora;
    }


    
    
}
