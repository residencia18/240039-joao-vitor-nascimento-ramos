import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteFORM(
        @NotBlank
        String nome,

        @NotBlank
        @CPF
        String cpf,

        @NotBlank
        @Email
        String email
) {

}
