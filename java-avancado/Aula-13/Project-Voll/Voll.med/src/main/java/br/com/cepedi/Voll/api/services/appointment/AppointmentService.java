package br.com.cepedi.Voll.api.services.appointment;

import br.com.cepedi.Voll.api.model.entitys.Appointment;
import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.input.DataCancelAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.details.DataDetailsAppointment;
import br.com.cepedi.Voll.api.repository.AppointmentRepository;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import br.com.cepedi.Voll.api.services.appointment.validations.register.ValidationAcheduleAppointment;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private DoctorRepository repositoryDoctors;

    @Autowired
    private PatientRepository repositoryPatient;

    @Autowired
    private List<ValidationAcheduleAppointment> validators;

    public DataDetailsAppointment register(DataRegisterAppointment data ){
        validators.forEach(validator -> validator.validation(data));
        Patient patient = repositoryPatient.getReferenceById(data.idPatient());
        Doctor doctor = chooseDoctor(data);
        Appointment appointment = new Appointment(null, doctor, patient, data.date(),null);
        repository.save(appointment);
        return new DataDetailsAppointment(appointment);
    }


    private Doctor chooseDoctor(DataRegisterAppointment data) {
        if (data.idDoctor() != null) {
            return repositoryDoctors.getReferenceById(data.idDoctor());
        }

        if (data.specialty() == null) {
            throw new ValidationException("Specialty is required when doctor is not chosen");
        }

        Doctor doctor = repositoryDoctors.chooseDoctorRandomFreethisDate(data.specialty(), data.date());

        if (doctor == null) {
            throw new ValidationException("No doctors available");
        }

        return doctor;
    }

    public void cancel(DataCancelAppointment data) {
        if (!repository.existsById(data.idAppointment())) {
            throw new ValidationException("The provided appointment ID does not exist!");
        }

        var appointment = repository.getReferenceById(data.idAppointment());
        appointment.cancel(data.reason());
    }
}
