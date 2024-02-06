package br.com.cepedi.model;

import java.util.Objects;

public class Estudante {
    private String nome;
    private int idade;
    private String curso;

    public Estudante(String nome, int idade, String curso) {
        this.nome = nome;
        this.idade = idade;
        this.curso = curso;
    }

    // Getters e Setters

    @Override
    public String toString() {
        return "Estudante{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", curso='" + curso + '\'' +
                '}';
    }
    
    

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Override
    public int hashCode() {
        return Objects.hash(nome, idade, curso);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Estudante estudante = (Estudante) obj;
        return idade == estudante.idade &&
                Objects.equals(nome, estudante.nome) &&
                Objects.equals(curso, estudante.curso);
    }
}
