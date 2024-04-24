package br.com.cepedi.Voll.api.services.doctor;

import br.com.cepedi.Voll.api.model.entitys.Doctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.details.DataDetailsDoctor;
import br.com.cepedi.Voll.api.repository.DoctorRepository;
import br.com.cepedi.Voll.api.services.doctor.validations.disabled.ValidationDisabledDoctor;
import br.com.cepedi.Voll.api.services.doctor.validations.update.ValidationUpdateDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private List<ValidationDisabledDoctor> validationsDisabled;

    @Autowired
    private List<ValidationUpdateDoctor> validationsUpdate;




    public DataDetailsDoctor register(DataRegisterDoctor data){
        Doctor doctor = new Doctor(data);
        repository.save(doctor);
        return new DataDetailsDoctor(doctor);
    }

    public Page<DataDetailsDoctor> list(Pageable pageable){
        return repository.findAllByActivatedTrue(pageable).map(DataDetailsDoctor::new);
    }

    public  DataDetailsDoctor details(Long id){
        return new DataDetailsDoctor(repository.getReferenceById(id));
    }

    public DataDetailsDoctor update(DataUpdateDoctor data){
        validationsUpdate.forEach(v -> v.validation(data));
        Doctor doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);
        return new DataDetailsDoctor(doctor);
    }

    public void disabled(Long id){
        validationsDisabled.forEach(v -> v.validation(id));
        Doctor doctor = repository.getReferenceById(id);
        doctor.logicalDelete();
    }

}
