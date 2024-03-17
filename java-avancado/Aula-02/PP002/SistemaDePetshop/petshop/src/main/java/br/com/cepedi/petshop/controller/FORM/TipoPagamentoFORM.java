package br.com.cepedi.petshop.controller.FORM;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.validation.constraints.NotBlank;

public record TipoPagamentoFORM(
        @NotNull
        @NotBlank
        String nome
) {

	
}