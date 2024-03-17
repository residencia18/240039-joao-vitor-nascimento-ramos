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
		
		@NotBlank
		String nome,
		
		@NotBlank
		@CPF
		String cpf,
		
		@NotBlank
		@Email
		String email
		
		)

{
	
}
