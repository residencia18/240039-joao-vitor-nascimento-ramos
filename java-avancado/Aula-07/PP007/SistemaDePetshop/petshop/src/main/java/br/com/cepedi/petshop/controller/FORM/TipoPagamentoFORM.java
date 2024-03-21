package br.com.cepedi.petshop.controller.FORM;

import jakarta.validation.constraints.NotBlank;

public record TipoPagamentoFORM(
        @NotBlank(message = "O nome do tipo de pagamento é obrigatorio") String nome
) {}