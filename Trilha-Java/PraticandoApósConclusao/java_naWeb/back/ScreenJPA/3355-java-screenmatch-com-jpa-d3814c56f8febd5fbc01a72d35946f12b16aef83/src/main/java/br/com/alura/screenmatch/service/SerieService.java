package br.com.alura.screenmatch.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.controller.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;

@Service
public class SerieService {

	@Autowired
	private SerieRepository repositorio;

	public List<SerieDTO> obterTodasAsSeries() {
		return converteSeriesParaDTO(repositorio.findAll());
	}

	public List<SerieDTO> obterTop5() {
		return converteSeriesParaDTO(repositorio.findTop5ByOrderByAvaliacaoDesc());
	}

	public List<SerieDTO> obterLancamentos() {
		return converteSeriesParaDTO(repositorio.encontrarEpisodiosMaisRecentes());
	}

	private List<SerieDTO> converteSeriesParaDTO(List<Serie> series) {
		return series.stream().map(SerieDTO::new).collect(Collectors.toList());
	}

	private List<EpisodioDTO> converteEpisodiosParaDTO(List<Episodio> episodios) {
		return episodios.stream().map(EpisodioDTO::new).collect(Collectors.toList());
	}

	private SerieDTO converteDados(Serie s) {
		return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(),
				s.getAtores(), s.getPoster(), s.getSinopse());
	}

	public SerieDTO obterPorId(Long id) {
		Optional<Serie> serie = repositorio.findById(id);
		if (serie.isPresent()) {
			return converteDados(serie.get());
		} else {
			return null;
		}

	}

	public List<EpisodioDTO> obterTodosEpsodios(Long id) {
		Optional<Serie> serieOptional = repositorio.findById(id);
		if (serieOptional.isPresent()) {
			Serie serie = serieOptional.get();
			return converteEpisodiosParaDTO(serie.getEpsodios());
		} else {
			return null;
		}
	}

	public List<EpisodioDTO> obterTemporadasPorNumero(Long id, Long numero) {
		List<Episodio> episodios = repositorio.obterEpisodiosPorTemporada(id, numero);
		return converteEpisodiosParaDTO(episodios);
	}

	public List<SerieDTO> obterSeriesPorCategoria(String nomeGenero) {
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        return converteSeriesParaDTO(repositorio.findByGenero(categoria));
}
}
