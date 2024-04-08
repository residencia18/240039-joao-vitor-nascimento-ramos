package br.com.alura.screesound.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.screesound.model.Artista;
import br.com.alura.screesound.model.Musica;

public interface ArtistaRepository extends JpaRepository<Artista,Long> {

	Optional<Artista> findByNomeContainingIgnoreCase(String nome);
	
    @Query("SELECT mu FROM Artista a JOIN a.musicas mu WHERE a.nome ILIKE %:nome%")
    List<Musica> buscaMusicasPorArtista(String nome);

	
}
