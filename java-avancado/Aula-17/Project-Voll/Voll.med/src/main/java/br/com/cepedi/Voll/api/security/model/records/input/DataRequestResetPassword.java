package br.com.cepedi.Voll.api.security.model.records.input;

import jakarta.validation.constraints.NotBlank;

public record DataRequestResetPassword(

        @NotBlank
        String email

) {
}
