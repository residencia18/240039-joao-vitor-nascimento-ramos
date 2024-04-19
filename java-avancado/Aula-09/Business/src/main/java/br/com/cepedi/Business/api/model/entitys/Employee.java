package br.com.cepedi.Business.api.model.entitys;

import br.com.cepedi.Business.api.model.embeddables.Address;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Role role;

    private String email;

    private String cpf;

    private LocalDate birthday;

    private String phoneNumber;

    @Embedded
    private Address address;

    private Boolean activated;


}
