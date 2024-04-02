package com.biblioteca.biblioteca.controller.FORM;

import com.biblioteca.biblioteca.model.Autor;

import jakarta.validation.constraints.NotBlank;

public record AutorFORM(
		
		@NotBlank(message = "O nome do autor é obrigatorio")
		String nome
		
		
		)
{
	

}
