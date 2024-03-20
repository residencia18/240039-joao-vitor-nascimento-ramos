package br.com.cepedi.petshop.controller.FORM;

import jakarta.validation.constraints.NotBlank;

public record PagamentoFORM(
        @NotBlank Long idTipoPagamento,
        @NotBlank Long idVenda
) {}