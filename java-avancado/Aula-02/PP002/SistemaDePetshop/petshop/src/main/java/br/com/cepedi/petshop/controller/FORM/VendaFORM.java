package br.com.cepedi.petshop.controller.FORM;

import jakarta.validation.constraints.NotNull;

public record VendaFORM(
        @NotNull
        Long idCliente
) {

}
