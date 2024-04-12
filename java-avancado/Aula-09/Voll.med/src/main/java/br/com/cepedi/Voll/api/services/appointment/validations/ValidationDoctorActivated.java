package br.com.cepedi.Voll.api.services.appointment.validations;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationDoctorActivated implements  ValidationAcheduleAppointment{

    @Autowired
    private DoctorRepository repositoryDoctor;

    public void validation(DataRegisterAppointment data){

        if(data.idDoctor() == null){
            return;
        }

        Boolean doctorActivated = repositoryDoctor.findActivatedById(data.idDoctor());
        if(!doctorActivated){
            throw new ValidationException("Appointment cannot be scheduled with a deactivated doctor");
        }

    }
}
