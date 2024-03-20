package br.com.cepedi.petshop.controller.FORM;

import java.math.BigInteger;

import org.antlr.v4.runtime.misc.NotNull;

public record VendaProdutoFORM(
        @NotNull Long idProduto,
        @NotNull BigInteger quantidade
) {}
