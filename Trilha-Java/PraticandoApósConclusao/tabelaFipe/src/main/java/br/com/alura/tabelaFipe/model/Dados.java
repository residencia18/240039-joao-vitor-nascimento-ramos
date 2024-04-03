package br.com.alura.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Dados (
		@JsonAlias("codigo") String codigo,
		@JsonAlias("nome")String nome
		)
{

	@Override
	public String toString() {
		return "[codigo=" + codigo + ", nome=" + nome + "]";
	}

}
