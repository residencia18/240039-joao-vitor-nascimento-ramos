package br.com.cepedi.Voll.api.model.records.appointment.input;

import br.com.cepedi.Voll.api.model.records.doctor.input.doctor.Specialty;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataRegisterAppointment(


        Long idDoctor,


        @NotNull
        Long idPatient,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime date,


        Specialty specialty

) {
}
