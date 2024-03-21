package com.biblioteca.biblioteca.model;

import com.biblioteca.biblioteca.Exceptions.CPFInvalidoException;
import com.biblioteca.biblioteca.Exceptions.NomeInvalidoException;
import com.cepedi.biblioteca.verificacoes.Verificacoes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="CLIENTE")
@Getter
@NoArgsConstructor
@ToString
public class Cliente {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter
    private Long id;
    
    @Column(name="NOME")
    private String nome;
    
    @Column(name = "CPF", unique = true , nullable = false)
    private String cpf;

    @Column(name = "EMAIL", unique = true , nullable = false)
    private String email;

    public Cliente(Cliente cliente) {
        super();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
    }



    public void setNome(String nome) throws NomeInvalidoException {
        
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        
        if(!Verificacoes.verificarNomeSemNumeros(nome)) {
            throw new NomeInvalidoException();
        }
        this.nome = nome;
    }



    public void setCpf(String cpf) throws CPFInvalidoException {
        
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório");
        }
        if(!Verificacoes.validarCPF(cpf)) {
            throw new CPFInvalidoException();
        }
        
        this.cpf = cpf.replaceAll("[^0-9]", "");
    }


    public void setEmail(String email) {
    	
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("EMAIL é obrigatório");
        }
    	
        this.email = email;
    }


    
    
    
}
