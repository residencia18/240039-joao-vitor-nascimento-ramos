package com.biblioteca.biblioteca.controller.FORM;


import org.hibernate.validator.constraints.br.CPF;
import org.springframework.boot.context.properties.bind.Name;

import com.biblioteca.biblioteca.Exceptions.CPFInvalidoException;
import com.biblioteca.biblioteca.Exceptions.NomeInvalidoException;
import com.biblioteca.biblioteca.model.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


public record ClienteFORM(
		
		@NotBlank(message = "O nome do cliente é obrigatorio")
		String nome,
		
		@NotBlank(message = "CPF é obrigatorio")
		@CPF(message = "CPF inválido")
		String cpf,
		
		@NotBlank(message = "Email é obrigatorio")
		@Email(message = "Email inválido")
		String email
		
		)

{
	
}
