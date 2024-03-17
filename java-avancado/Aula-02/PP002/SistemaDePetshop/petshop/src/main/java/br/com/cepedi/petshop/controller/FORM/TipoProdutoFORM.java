package br.com.cepedi.petshop.controller.FORM;


import jakarta.validation.constraints.NotBlank;
import br.com.cepedi.petshop.model.TipoProduto;

public record TipoProdutoFORM(
        @NotBlank
        String nome
) {

}
