package br.com.cepedi.Voll.api.audit.record;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DataRegisterAudit(

        @NotNull
        String eventName,

        String eventDescription,

        @NotNull
        Long userId,

        @NotNull
        String userName,

        String affectedResource,

        @NotNull
        @Positive
        String origin


) {
}
