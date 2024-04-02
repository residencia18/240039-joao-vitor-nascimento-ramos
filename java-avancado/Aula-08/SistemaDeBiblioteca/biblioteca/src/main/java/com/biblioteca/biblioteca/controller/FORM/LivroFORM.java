package com.biblioteca.biblioteca.controller.FORM;

import org.antlr.v4.runtime.misc.NotNull;

import com.biblioteca.biblioteca.model.Livro;

import jakarta.validation.constraints.NotBlank;

public record LivroFORM(
		@NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "É obrigatorio um autor válido") Long id_autor,
        @NotBlank(message = "É obrigatorio uma editora válida")  Long id_editora
) {


	

}
