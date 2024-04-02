package br.com.cepedi.petshop.controller.FORM;

import jakarta.validation.constraints.NotBlank;

public record TipoProdutoFORM(
		@NotBlank(message = "O nome do tipo de produto é obrigatorio") String nome
) {}