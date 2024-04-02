package br.com.cepedi.petshop.controller.FORM;

import jakarta.validation.constraints.NotBlank;

public record MarcaFORM(
		@NotBlank(message = "O nome da marca é obrigatorio")
		String nome
	) {}
