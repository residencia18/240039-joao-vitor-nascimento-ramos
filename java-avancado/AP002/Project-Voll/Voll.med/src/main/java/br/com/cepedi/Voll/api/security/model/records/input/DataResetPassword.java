package br.com.cepedi.Voll.api.security.model.records.input;

import br.com.cepedi.Voll.api.security.model.validations.Password;
import jakarta.validation.constraints.NotBlank;

public record DataResetPassword(

        @NotBlank
        String token,

        @Password
        String password

) {
}
