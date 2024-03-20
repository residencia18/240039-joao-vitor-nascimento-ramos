package br.com.cepedi.petshop.controller.FORM;

import jakarta.validation.constraints.NotBlank;

public record MarcaFORM(
	    @NotBlank
	    String nome
	) {}
