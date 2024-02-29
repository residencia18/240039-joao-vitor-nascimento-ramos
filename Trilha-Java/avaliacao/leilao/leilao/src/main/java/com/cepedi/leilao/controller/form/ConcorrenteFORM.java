package com.cepedi.leilao.controller.form;

import com.cepedi.leilao.model.Concorrente;

public class ConcorrenteFORM {
    
    private String nome;
    private String cpf;

    // Construtores, Getters e Setters
    public ConcorrenteFORM() {}

    public ConcorrenteFORM(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "ConcorrenteForm{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
    
    public Concorrente toConcorrente() {
    	Concorrente concorrente = new Concorrente();
    	concorrente.setNome(nome);
    	concorrente.setCpf(cpf);
    	return concorrente;
    	
    }
}
