package com.biblioteca.biblioteca.controller.FORM;

import org.antlr.v4.runtime.misc.NotNull;

import com.biblioteca.biblioteca.model.Livro;

import jakarta.validation.constraints.NotBlank;

public record LivroFORM(
        @NotBlank String nome,
        Long id_autor,
        Long id_editora
) {


	

}
