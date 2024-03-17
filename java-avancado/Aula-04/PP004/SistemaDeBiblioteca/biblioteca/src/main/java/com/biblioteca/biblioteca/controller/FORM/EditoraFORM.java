package com.biblioteca.biblioteca.controller.FORM;

import com.biblioteca.biblioteca.model.Editora;

import jakarta.validation.constraints.NotBlank;

public record EditoraFORM(
		
		@NotBlank
		String nome
) 


{
	
	
}
