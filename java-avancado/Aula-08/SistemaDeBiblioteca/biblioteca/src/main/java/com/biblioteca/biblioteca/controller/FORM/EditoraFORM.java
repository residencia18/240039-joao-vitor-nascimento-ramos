package com.biblioteca.biblioteca.controller.FORM;

import com.biblioteca.biblioteca.model.Editora;

import jakarta.validation.constraints.NotBlank;

public record EditoraFORM(
		
		@NotBlank(message = "O nome da editora é obrigatorio")
		String nome
) 


{
	
	
}
