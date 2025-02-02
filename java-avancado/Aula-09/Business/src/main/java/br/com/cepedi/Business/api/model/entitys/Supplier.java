package br.com.cepedi.Business.api.model.entitys;


import br.com.cepedi.Business.api.model.embeddables.Address;
import br.com.cepedi.Business.api.model.records.supplier.input.DataRegisterSupplier;
import br.com.cepedi.Business.api.model.records.supplier.input.DataUpdateSupplier;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String CPF;

    private String CNPJ;

    private String email;

    private String phoneNumber1;

    private String phoneNumber2;

    private Boolean activated;

    @Embedded
    private Address address;


    public Supplier(DataRegisterSupplier data) {
        this.name = data.name();
        this.email = data.email();
        this.phoneNumber1 = data.phoneNumber1();
        this.phoneNumber2 = data.phoneNumber2();

        validateCPFAndCNPJ(data.CPF(), data.CNPJ());

        if (data.CPF() != null && !data.CPF().isEmpty()) {
            this.CPF = data.CPF();
        } else if (data.CNPJ() != null && !data.CNPJ().isEmpty()) {
            this.CNPJ = data.CNPJ();
        } else {
            throw new IllegalArgumentException("It is necessary to provide a CPF or CNPJ to the supplier.");
        }

        this.address = new Address(data.dataRegisterAddress());
        this.activated = true;
    }

    private void validateCPFAndCNPJ(String CPF, String CNPJ) {
        if ((CPF != null && !CPF.isEmpty()) && (CNPJ != null && !CNPJ.isEmpty())) {
            throw new IllegalArgumentException("It is permitted to provide only a CPF or a CNPJ to the supplier, not both.");
        }
    }


    public void updateData(DataUpdateSupplier data){

        if(data.name() != null){
            this.name = data.name();
        }

        validateCPFAndCNPJ(data.CPF(), data.CNPJ());

        if (data.CPF() != null && !data.CPF().isEmpty()) {
            this.CPF = data.CPF();
        } else if (data.CNPJ() != null && !data.CNPJ().isEmpty()) {
            this.CNPJ = data.CNPJ();
        }

        if(data.email() != null && !data.email().isEmpty()){
            this.email = data.email();
        }

        if(data.phoneNumber1() != null && !data.phoneNumber1().isEmpty()){
            this.phoneNumber1 = data.phoneNumber1();
        }

        if(data.phoneNumber2() != null && !data.phoneNumber2().isEmpty()){
            this.phoneNumber2 = data.phoneNumber2();
        }

        if(data.dataRegisterAddress() != null){
            this.address = new Address(data.dataRegisterAddress());
        }


    }

    public void logicalDelete(){
        this.activated = false;
    }

}