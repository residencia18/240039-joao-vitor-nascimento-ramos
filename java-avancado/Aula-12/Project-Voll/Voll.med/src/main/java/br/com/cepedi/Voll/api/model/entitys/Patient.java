package br.com.cepedi.Voll.api.model.entitys;

import br.com.cepedi.Voll.api.model.records.patient.input.DataRegisterPatient;
import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String cpf;
    @Embedded
    private Address address;

    private Boolean activated;

    public Patient(DataRegisterPatient data) {
        this.name = data.name();
        this.email = data.email();
        this.phoneNumber = data.phoneNumber();
        this.cpf = data.cpf();
        this.address = new Address(data.dataAddress());
        this.activated = true;
    }

    public void updateData(DataUpdatePatient data) {
        if (data.name() != null) {
            this.name = data.name();
        }

        if (data.phoneNumber() != null) {
            this.phoneNumber = data.phoneNumber();
        }

        if (data.dataAddress() != null) {
            this.address = new Address(data.dataAddress());
        }
    }

    public void logicalDelete() {
        this.activated = false;
    }
}
