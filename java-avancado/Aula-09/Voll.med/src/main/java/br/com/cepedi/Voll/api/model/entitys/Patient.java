package br.com.cepedi.Voll.api.model.entitys;

import br.com.cepedi.Voll.api.model.records.patients.input.DataRegisterPatient;
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

    public void updateData(String name, String email, String phoneNumber, String cpf, Address address) {
        if (name != null) {
            this.name = name;
        }

        if (email != null) {
            this.email = email;
        }

        if (phoneNumber != null) {
            this.phoneNumber = phoneNumber;
        }

        if (cpf != null) {
            this.cpf = cpf;
        }

        if (address != null) {
            this.address = address;
        }
    }

    public void logicalDelete() {
        this.activated = false;
    }
}
