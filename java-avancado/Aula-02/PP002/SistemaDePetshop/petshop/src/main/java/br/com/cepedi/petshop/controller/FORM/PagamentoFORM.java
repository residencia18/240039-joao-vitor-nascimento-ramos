package br.com.cepedi.petshop.controller.FORM;

import jakarta.validation.constraints.NotNull;

public record PagamentoFORM(
        @NotNull
        Long idTipoPagamento,
        
        @NotNull
        Long idVenda
) {

}
