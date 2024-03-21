package br.com.cepedi.petshop.controller.FORM;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;

public record ClienteFORM(
		@NotBlank(message = "O nome do cliente é obrigatorio")
		String nome,
		
		@NotBlank(message = "CPF é obrigatorio")
		@CPF(message = "CPF inválido")
		String cpf
	) {}