package br.com.alura.screenmatch.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

public class Principal {

	private Scanner leitura = new Scanner(System.in);
	private ConsumoApi consumo = new ConsumoApi();
	private ConverteDados conversor = new ConverteDados();
	private final String ENDERECO = "https://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=6585022c";

	private SerieRepository repositorySerie;

	private List<Serie> series = new ArrayList<>();

	public Principal(SerieRepository repositorySerie) {
		this.repositorySerie = repositorySerie;
	}

	public void exibeMenu() {
		var menu = """
				1 -  Buscar séries
				2 -  Buscar episódios
				3 -  Listar series buscadas
				4 -  Buscar serie por titulo
				5 -  Buscar series por ator
				6 -  Top 5 séries
				7 -  Buscar Series por categoria
				8 -  Buscar serie por quantidade de tempoarada e avaliacao
				9 -  Buscar episodios por trecho
				10 - Top Episodios por serie 
				11 - Buscar episodios após uma data
				0 - Sair
				""";
		int opcao = -1;

		do {
			System.out.println(menu);
			opcao = leitura.nextInt();
			leitura.nextLine();

			switch (opcao) {
			case 1:
				buscarSerieWeb();
				break;
			case 2:
				buscarEpisodioPorSerie();
				break;
			case 3:
				listarSeriesBuscadas();
				break;
			case 4:
				buscarSeriePorTitulo();
				break;
			case 5:
				buscarSeriePorAtor();
				break;
			case 6:
				buscarTop5Series();
				break;
			case 7:
				buscarPorGenero();
				break;
			case 8:
				buscarPorQtdTemporadaAndAvaliacao();
				break;
			case 9:
				buscarEpisodioPorTrecho();
				break;
			case 10:
				topEpisodiosPorSerie();
				break;
			case 11:
				buscarEpisodiosAposUmaData();
				break;
			case 0:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida");
			}
		} while (opcao != 0);

	}

	private void buscarEpisodiosAposUmaData() {
		System.out.println("Digite o nome da série para busca");
		var nomeSerie = leitura.nextLine();
		Optional<Serie> serieOptional = repositorySerie.findByTituloContainsIgnoreCase(nomeSerie);
		if (serieOptional.isPresent()) {
			System.out.println("Digite o ano limite de lançamento : ");
			int ano = leitura.nextInt();
			leitura.nextLine();
			Serie serie = serieOptional.get();
			List<Episodio> episodios = repositorySerie.buscarEpidiosAposUmaData(serie,ano);
			episodios.forEach(System.out::println);
		} else {
			System.out.println("Serie nao encontrada");
		}		
		
	}

	private void topEpisodiosPorSerie() {
		System.out.println("Digite o nome da série para busca");
		var nomeSerie = leitura.nextLine();
		Optional<Serie> serieOptional = repositorySerie.findByTituloContainsIgnoreCase(nomeSerie);
		if (serieOptional.isPresent()) {
			Serie serie = serieOptional.get();
			List<Episodio> episodios = repositorySerie.toEpisodiosPorSerie(serie,5);
			episodios.forEach(System.out::println);
		} else {
			System.out.println("Serie nao encontrada");
		}		
	}

	private void buscarEpisodioPorTrecho() {
		System.out.println("Digite o nome do episodios para busca: ");
		String texto = leitura.nextLine();
		
		List<Episodio> epsidiosEncontrados = repositorySerie.episodiosPorTrecho(texto);
		epsidiosEncontrados.forEach(System.out::println);
		
	}

	private void buscarPorQtdTemporadaAndAvaliacao() {
		System.out.println("Digite a quantidade de temporadas maxima");
		int qtdTemporada = leitura.nextInt();
		leitura.nextLine();
		System.out.println("Avaliacoes apartir de que valor ? ");
		Double avaliacao = leitura.nextDouble();
		List<Serie> series = repositorySerie.seriesPorTemporadaEAvaliacao(qtdTemporada,avaliacao);
		if (!series.isEmpty()) {
			series.stream().forEach(System.out::println);
		} else {
			System.out.println("Nenhuma serie encontrada");
		}
		
	}

	private void buscarTop5Series() {
		List<Serie> series = repositorySerie.findTop5ByOrderByAvaliacaoDesc();
		series.stream().forEach(System.out::println);
	}

	private void buscarPorGenero() {
		System.out.println("Digite o nome do genero para busca");
		String nomeCategoria = leitura.nextLine();
		Categoria categoria = Categoria.fromPortugues(nomeCategoria);
		List<Serie> series = repositorySerie.findByGenero(categoria);

		if (!series.isEmpty()) {
			series.stream().forEach(System.out::println);
		} else {
			System.out.println("Genero não encontrado em nenhuma serie");
		}
	}

	private void buscarSeriePorTitulo() {
		System.out.println("Digite o nome da série para busca");
		var nomeSerie = leitura.nextLine();
		Optional<Serie> serieOptional = repositorySerie.findByTituloContainsIgnoreCase(nomeSerie);
		if (serieOptional.isPresent()) {
			Serie serie = serieOptional.get();
			System.out.println(serie);
		} else {
			System.out.println("Serie nao encontrada");
		}

	}

	private void buscarSeriePorAtor() {
		System.out.println("Digite o nome do ator para busca");
		var nomeAtor = leitura.nextLine();
		System.out.println("Avaliacoes apartir de que valor ? ");
		var avaliacao = leitura.nextDouble();
		List<Serie> series = repositorySerie.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor,
				avaliacao);
		if (!series.isEmpty()) {
			series.stream().forEach(System.out::println);
		} else {
			System.out.println("Ator não em nenhuma serie");
		}
	}

	private void listarSeriesBuscadas() {
		this.series = repositorySerie.findAll();
		this.series.forEach(System.out::println);

	}

	private void buscarSerieWeb() {
		DadosSerie dados = getDadosSerie();
		Serie serie = new Serie(dados);
		repositorySerie.save(serie);
	}

	private DadosSerie getDadosSerie() {
		System.out.println("Digite o nome da série para busca");
		var nomeSerie = leitura.nextLine();
		var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		return dados;
	}

	private void buscarEpisodioPorSerie() {
		listarSeriesBuscadas();
		System.out.println("Qual serie deseja buscar (pelo nome) ? ");
		String nomeSerie = leitura.nextLine();
		Optional<Serie> serieOptional = repositorySerie.findByTituloContainsIgnoreCase(nomeSerie);

		if (serieOptional.isPresent()) {
			Serie serieEncontrada = serieOptional.get();
			List<DadosTemporada> temporadas = new ArrayList<>();
			for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
				var json = consumo.obterDados(
						ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
				DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
				temporadas.add(dadosTemporada);
			}

			List<Episodio> episodios = temporadas.stream()
					.flatMap(t -> t.episodios().stream().map(e -> new Episodio(t.numero(), e)))
					.collect(Collectors.toList());
			serieEncontrada.setEpsodios(episodios);
			repositorySerie.save(serieEncontrada);

		} else {
			System.out.println("Serie não encontrada");
		}

	}
}