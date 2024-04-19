package br.com.cepedi.Business.api.model.records.productType.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterProductType(

        @NotBlank
        String name

) {
}
