package br.com.cepedi.Library.api.model.entitys;


import br.com.cepedi.Library.api.model.records.loan.input.DataRegisterLoan;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean activated;


    public Loan(Client client, Book book){
        this.book = book;
        this.client = client;
        this.startDate = LocalDateTime.now();
        this.activated = true;
    }

    public void updateData(Client client , Book book) {

        if (client != null) {
            this.client = client;
        }

        if (book != null) {
            this.book = book;
        }

    }


    public void finish() {
        this.endDate = LocalDateTime.now();
        this.activated = false;
    }


}
