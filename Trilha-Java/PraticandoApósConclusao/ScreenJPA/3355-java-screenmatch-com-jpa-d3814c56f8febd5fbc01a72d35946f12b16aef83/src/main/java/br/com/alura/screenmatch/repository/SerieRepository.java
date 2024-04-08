package br.com.alura.screenmatch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;

public interface SerieRepository extends JpaRepository<Serie,Long>{
	Optional<Serie> findByTituloContainsIgnoreCase(String nomeSerie);
	List<Serie> findByAtoresContainingIgnoreCase(String nomeAutor);
	List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAutor, Double avaliacao);
	List<Serie> findTop5ByOrderByAvaliacaoDesc();
	List<Serie> findByGenero(Categoria categoria);
	
	List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int numeroTemporadas, Double avaliacao);

	@Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :numeroTemporadas AND s.avaliacao >= :avaliacao")
	List<Serie> seriesPorTemporadaEAvaliacao(int numeroTemporadas, Double avaliacao);
	
	@Query("SELECT e FROM Serie s JOIN s.epsodios e WHERE e.titulo ILIKE %:texto%")
	List<Episodio> episodiosPorTrecho(String texto);
	
	@Query("SELECT e FROM Serie s JOIN s.epsodios e WHERE s =:serie ORDER BY e.avaliacao DESC LIMIT :limite")
	List<Episodio> toEpisodiosPorSerie(Serie serie,int limite);
	
	@Query("SELECT e FROM Serie s JOIN s.epsodios e WHERE s = :serie AND YEAR(e.dataLancamento) >= :ano")
	List<Episodio> buscarEpidiosAposUmaData(Serie serie ,int ano);

	
}
