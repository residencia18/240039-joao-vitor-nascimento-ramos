package br.com.cepedi.petshop.controller.FORM;

import jakarta.validation.constraints.NotBlank;

public record VendaFORM(
    @NotBlank Long idCliente
) {}
