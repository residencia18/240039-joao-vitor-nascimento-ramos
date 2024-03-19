package com.biblioteca.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="LIVRO")
public class Livro {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="NOME" , nullable = false)
    private String nome;
    
    @ManyToOne
    @JoinColumn(name="AUTOR_ID" , nullable = false)
    private Autor autor;
    
    @ManyToOne
    @JoinColumn(name="EDITORA_ID" , nullable = false)
    private Editora editora;
    
    public Livro() {
        super();
    }
    
    
    public Livro(Livro livro) {
        this.id = livro.id;
        this.nome = livro.nome;
        this.autor = livro.autor;
        this.editora = livro.editora;
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
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        if (autor == null) {
            throw new IllegalArgumentException("Autor é obrigatório");
        }
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        if (editora == null) {
            throw new IllegalArgumentException("Editora é obrigatória");
        }
        this.editora = editora;
    }
}
