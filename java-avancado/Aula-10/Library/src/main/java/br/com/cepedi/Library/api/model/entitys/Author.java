package br.com.cepedi.Library.api.model.entitys;

import br.com.cepedi.Library.api.model.records.author.input.DataRegisterAuthor;
import br.com.cepedi.Library.api.model.records.author.input.DataUpdateAuthor;
import br.com.cepedi.Library.api.model.records.client.input.DataRegisterClient;
import br.com.cepedi.Library.api.model.records.client.input.DataUpdateClient;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    private Boolean activated;

    @OneToMany(mappedBy = "author")
    private List<Book> books;


    public Author(DataRegisterAuthor data) {
        this.name = data.name();
        this.activated = true;
    }

    public void updateData(DataUpdateAuthor data) {
        if (data.name() != null) {
            this.name = data.name();
        }
    }

    public void logicalDelete() {
        this.activated = false;
    }


}
