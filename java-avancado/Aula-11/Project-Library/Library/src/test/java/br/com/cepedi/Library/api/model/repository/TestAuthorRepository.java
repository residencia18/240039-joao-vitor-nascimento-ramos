package br.com.cepedi.Library.api.model.repository;

import br.com.cepedi.Library.api.model.entitys.Author;
import br.com.cepedi.Library.api.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestAuthorRepository {

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    public void setUp() {
        // Limpar o banco de dados antes de cada teste
        authorRepository.deleteAll();

        // Salvar autores no banco de dados
        Author author1 = new Author();
        author1.setId(1L);
        author1.setActivated(true);
        authorRepository.save(author1);

        Author author2 = new Author();
        author2.setId(2L);
        author2.setActivated(false);
        authorRepository.save(author2);
    }

    @Test
    public void testFindAllByActivatedTrue() {
        Page<Author> authors = authorRepository.findAllByActivatedTrue(Pageable.unpaged());
        assertEquals(1, authors.getTotalElements()); // Verifica se há um autor na página
    }

    @Test
    public void testFindActivatedById() {
        boolean activated1 = authorRepository.findActivatedById(1L);
        boolean activated2 = authorRepository.findActivatedById(2L);

        assertEquals(true, activated1); // Verifica se o autor com ID 1 está ativado
        assertEquals(false, activated2); // Verifica se o autor com ID 2 não está ativado
    }
}