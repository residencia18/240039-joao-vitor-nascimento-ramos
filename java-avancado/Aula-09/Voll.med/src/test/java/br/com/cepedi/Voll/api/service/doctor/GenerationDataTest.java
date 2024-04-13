package br.com.cepedi.Voll.api.service.doctor;


import br.com.cepedi.Voll.api.faker.PtBRCpfIdNumber;
import br.com.cepedi.Voll.api.model.records.address.DataAddress;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class GenerationDataTest {

    private static final Faker faker = new Faker();

    private PtBRCpfIdNumber cpfGenerator = new PtBRCpfIdNumber();


    public DataRegisterDoctor generationDoctorRandomWithSpecialityDefined(Specialty specialty) {
        return new DataRegisterDoctor(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.phoneNumber().phoneNumber(),
                faker.number().digits(6),
                specialty,
                createAddressData()
        );
    }

    public DataRegisterPatient generationPatientRandom() {
        return new DataRegisterPatient(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.phoneNumber().phoneNumber(),
                generationCPF(),
                createAddressData()
        );
    }


    public DataAddress createAddressData() {
        return new DataAddress(
                faker.address().streetName(),
                faker.address().city(),
                faker.number().digits(8),
                faker.address().city(),
                "XX",
                null,
                null
        );
    }

    private String generationCPF() {
        return cpfGenerator.getValidFormattedCpf(faker);
    }


}
