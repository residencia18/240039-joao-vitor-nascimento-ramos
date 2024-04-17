package br.com.cepedi.Library.api.model.entitys;


import br.com.cepedi.Library.api.model.records.book.input.DataRegisterBook;
import br.com.cepedi.Library.api.model.records.client.input.DataUpdateClient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer anoPublicacao;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    private Boolean activated;

    @OneToMany(mappedBy = "book" , fetch = FetchType.EAGER)
    private List<Loan> loans = new ArrayList<>();

    public Book(String name, Integer anoPublicacao, Author author , Publisher publisher){
        this.name = name;
        this.anoPublicacao = anoPublicacao;
        this.author = author;
        this.publisher = publisher;
        this.activated = true;
    }





    public void logicalDelete() {
        this.activated = false;
    }

    public void updateData(String name, Integer anoPublicacao, Author author , Publisher publisher) {
        if (name != null) {
            this.name = name;
        }
        if (anoPublicacao != null) {
            this.anoPublicacao = anoPublicacao;
        }

        if (author != null) {
            this.author = author;
        }

        if (publisher != null) {
            this.publisher = publisher;
        }

    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", author=" + (author != null ? "Author(id=" + author.getId() + ", name=" + author.getName() + ")" : null) +
                ", publisher=" + (publisher != null ? "Publisher(id=" + publisher.getId() + ", name=" + publisher.getName() + ")" : null) +
                ", activated=" + activated +
                ", loans=" + loans +
                '}';
    }

}
