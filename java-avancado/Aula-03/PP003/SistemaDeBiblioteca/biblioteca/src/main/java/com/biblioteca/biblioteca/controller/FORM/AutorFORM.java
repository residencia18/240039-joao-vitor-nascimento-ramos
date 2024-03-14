package com.biblioteca.biblioteca.controller.FORM;

import com.biblioteca.biblioteca.model.Autor;

import jakarta.validation.constraints.NotBlank;

public record AutorFORM(
		
		@NotBlank
		String nome
		
		
		)
{
	

}
