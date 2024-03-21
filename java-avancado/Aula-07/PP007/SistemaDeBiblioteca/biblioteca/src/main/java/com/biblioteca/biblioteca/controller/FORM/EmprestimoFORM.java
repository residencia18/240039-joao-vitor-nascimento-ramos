package com.biblioteca.biblioteca.controller.FORM;

import java.time.LocalDateTime;

import com.biblioteca.biblioteca.model.Emprestimo;

import jakarta.validation.constraints.NotBlank;

public record EmprestimoFORM(

		@NotBlank(message = "É obrigatorio um livro válido") Long id_livro,

		@NotBlank(message = "É obrigatorio um cliente válido") Long id_cliente,

		LocalDateTime data_emprestimo

		, LocalDateTime data_devolucao) {

	    public EmprestimoFORM {
	        if (data_devolucao != null && data_devolucao.isBefore(data_emprestimo)) {
	            throw new IllegalArgumentException("A data de devolução deve ser após a data de empréstimo.");
	        }
	    }
	

}
