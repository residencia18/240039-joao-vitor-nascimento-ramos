package br.com.cepedi.Voll.api.services.patient;

import br.com.cepedi.Voll.api.model.entitys.Patient;
import br.com.cepedi.Voll.api.model.records.doctor.output.DataDetailsDoctor;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import br.com.cepedi.Voll.api.model.records.patient.output.DataDetailsPatient;
import br.com.cepedi.Voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientService {


    @Autowired
    private PatientRepository repository;


    public DataDetailsPatient register(DataRegisterPatient data){
        Patient patient = new Patient(data);
        repository.save(patient);
        return new DataDetailsPatient(patient);
    }

    public Page<DataDetailsPatient> list(Pageable pageable) {
        return repository.findAllByActivatedTrue(pageable).map(DataDetailsPatient::new);
    }
    public DataDetailsPatient details(Long id){
        Patient patient = repository.getReferenceById(id);
        return new DataDetailsPatient(patient);
    }


    public DataDetailsPatient update(DataUpdatePatient data){
        Patient patient = repository.getReferenceById(data.id());
        patient.updateData(data);
        return new DataDetailsPatient(patient);
    }

    public void disabled(Long id) {
        Patient patient = repository.getReferenceById(id);
        patient.logicalDelete();
    }
}
