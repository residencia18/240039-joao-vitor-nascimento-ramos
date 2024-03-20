package br.com.cepedi.petshop.controller.FORM;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;

public record ClienteFORM(
	    @NotBlank
	    String nome,

	    @NotBlank
	    @CPF
	    String cpf
	) {}