package br.com.cepedi.petshop.controller.FORM;

import java.math.BigInteger;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;



public record VendaProdutoFORM(
		@NotNull(message = "O cliente é obrigatorio") Long idProduto,
        @NotNull(message = "Quantidade é obrigatorio")
		@Positive(message= "Quantidade deve ser maior que zero") BigInteger quantidade
) {}
