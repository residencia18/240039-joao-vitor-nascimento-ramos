package br.com.cepedi.Business.api.model.embeddables;


import br.com.cepedi.Business.api.model.Enums.State;
import br.com.cepedi.Business.api.model.records.address.input.DataRegisterAddress;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    private String publicPlace;

    private String neighborhood;

    private String cep;

    private String city;

    @Enumerated
    private State state;

    private String complement;

    private String number;

    public Address(DataRegisterAddress dataRegisterAddress) {
        this.publicPlace = dataRegisterAddress.publicPlace();
        this.neighborhood = dataRegisterAddress.neighborhood();
        this.cep = dataRegisterAddress.cep();
        this.city = dataRegisterAddress.city();
        this.state = dataRegisterAddress.state();
        this.complement = dataRegisterAddress.complement();
        this.number = dataRegisterAddress.number();
    }


    public void updateData(DataRegisterAddress data) {
        if (data.publicPlace() != null) {
            this.publicPlace = data.publicPlace();
        }
        if (data.neighborhood() != null) {
            this.neighborhood = data.neighborhood();
        }
        if (data.cep() != null) {
            this.cep = data.cep();
        }
        if (data.city() != null) {
            this.city = data.city();
        }
        if (data.state() != null) {
            this.state = data.state();
        }
        if (data.complement() != null) {
            this.complement = data.complement();
        }
        if (data.number() != null) {
            this.number = data.number();
        }
    }

}
