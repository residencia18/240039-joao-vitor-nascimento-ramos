package br.com.cepedi.petshop.controller.FORM;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import br.com.cepedi.petshop.exceptions.PrecoInvalidoException;
import br.com.cepedi.petshop.model.Produto;

public record ProdutoFORM(
        @NotNull
        String nome,
        
        @NotNull
        Long idTipoProduto,
        
        @NotNull
        Long idMarca,
        
        String descricao,
        
        @NotNull
        BigDecimal preco
) {

}
