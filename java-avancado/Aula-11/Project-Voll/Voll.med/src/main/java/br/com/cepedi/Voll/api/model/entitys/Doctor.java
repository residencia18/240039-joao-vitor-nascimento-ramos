package br.com.cepedi.Voll.api.model.entitys;

import br.com.cepedi.Voll.api.model.records.doctor.input.DataRegisterDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    String phoneNumber;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;

    private Boolean activated;

    public Doctor(DataRegisterDoctor data) {
        this.name = data.name();
        this.email = data.email();
        this.crm = data.crm();
        this.phoneNumber = data.phoneNumber();
        this.specialty = data.specialty();
        this.address = new Address(data.dataAddress());
        this.activated = true;

    }


    public void updateData(DataUpdateDoctor data){
        if(data.name() != null){
            this.name = data.name();
        }

        if(data.phoneNumber() != null){
            this.phoneNumber = data.phoneNumber();
        }

        if(data.dataAddress() != null){
            this.address.updateData(data.dataAddress());
        }

    }

    public void logicalDelete(){
        this.activated = false;
    }
}
