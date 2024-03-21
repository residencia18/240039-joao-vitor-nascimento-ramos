package br.com.cepedi.petshop.controller.FORM;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProdutoFORM(
        @NotBlank(message = "O nome do produto é obrigatorio") String nome,
        @NotBlank(message = "Tipo do produto é obrigatorio") Long idTipoProduto,
        @NotBlank(message = "Marca é obrigatorio") Long idMarca,
        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero") BigDecimal preco
) {

}