package br.com.cepedi.petshop.controller.FORM;

import java.math.BigInteger;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.validation.constraints.Min;

public record Venda_ProdutoFORM(
        @NotNull
        Long idProduto,
        
        @NotNull
        @Min(1)
        BigInteger quantidade
) {

	
}
