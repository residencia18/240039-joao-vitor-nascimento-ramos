package br.com.cepedi.Voll.api.services.appointment.validations;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationDoctorWithOtherAppointmentInSameDate implements  ValidationAcheduleAppointment {

    @Autowired
    private DoctorRepository repositoryDoctor;

    public void validation(DataRegisterAppointment data){
        Boolean thisOcupeted = repositoryDoctor.existsByDoctorIdAndData(data.idDoctor(),data.date());

        if(thisOcupeted){
            throw new ValidationException("Doctor already has another appointment scheduled for this same hour");
        }
    }
}
