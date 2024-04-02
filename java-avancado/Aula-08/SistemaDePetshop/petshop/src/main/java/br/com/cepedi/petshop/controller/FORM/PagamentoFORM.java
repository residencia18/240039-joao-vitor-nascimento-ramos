package br.com.cepedi.petshop.controller.FORM;

import jakarta.validation.constraints.NotBlank;

public record PagamentoFORM(
        @NotBlank(message = "O tipo de pagamento é obrigatório") Long idTipoPagamento,
        @NotBlank(message = "A venda é obrigatória") Long idVenda
) {}