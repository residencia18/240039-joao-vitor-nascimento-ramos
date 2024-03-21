package br.com.cepedi.petshop.controller.FORM;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public record ProdutoFORM(
        @NotBlank String nome,
        @NotBlank Long idTipoProduto,
        @NotBlank Long idMarca,
        String descricao,
        @NotBlank BigDecimal preco
) {

}