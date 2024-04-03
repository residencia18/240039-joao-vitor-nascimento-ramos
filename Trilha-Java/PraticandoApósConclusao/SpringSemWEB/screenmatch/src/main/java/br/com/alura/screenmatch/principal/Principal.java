package br.com.alura.screenmatch.principal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverterDados;

public abstract class Principal {

	private static Scanner sc = new Scanner(System.in);
	private static final String API_KEY = "&apikey=6585022c";
	private static final String ENDERECO = "https://www.omdbapi.com/?t=";
	private static List<DadosTemporada> temporadas = new ArrayList<>();
	private static DadosSerie dadosSerie;
	private static List<Episodio> episodios;

	public static void imprimirMenu() {
		System.out.println("Digite o nome da série para busca : ");
		String nomeSerie = sc.nextLine();
		String jsonSerie = buscaSerie(nomeSerie);

		dadosSerie = ConverterDados.obterDados(jsonSerie, DadosSerie.class);

		buscaTemporadasNaAPI(nomeSerie, dadosSerie.totalTemporadas());

		episodios = listaDeTodosEpisodios();

//		EpisodiosAPartirDoAno();
//		
//
//		TopEpisodios();
//		
		avaliacaoPorTemporadas();
		
		
		DoubleSummaryStatistics est = episodios.stream().filter(e -> e.getAvaliacao().compareTo(BigDecimal.ZERO) > 0).collect(Collectors.summarizingDouble(e -> e.getAvaliacao().doubleValue()));
		
		System.out.println(est.getMax());
		System.out.println(est.getCount());
		System.out.println(est.getSum());
		System.out.println(est.getAverage());
		System.out.println(est.getMin());

	}

	private static void avaliacaoPorTemporadas() {
		Map<Integer, Double> mediaAvaliacoes = episodios.stream()
				.filter(e -> e.getAvaliacao().compareTo(BigDecimal.ZERO) > 0)
				.collect(Collectors.groupingBy(Episodio::getTemporada, Collectors.mapping(
						episodio -> episodio.getAvaliacao().doubleValue(), Collectors.averagingDouble(a -> a))));

		
		mediaAvaliacoes.entrySet().stream().forEach(entry -> {
			Integer temporada = entry.getKey();
			Double media = entry.getValue();
            System.out.println("Temporada " + temporada + ": " + String.format("%.2f", media));
		});
		
	}

	private static void compararDesempenho() {
		System.out.println("Comparando desempenho das duas abordagens:");

		long startTimeStream = System.nanoTime();
		todasOcorrenciasDeUmEpisodio();
		long endTimeStream = System.nanoTime();
		long durationStream = (endTimeStream - startTimeStream) / 1_000_000;

		long startTimeParallel = System.nanoTime();
		todasOcorrenciasDeUmEpisodioParalelizado();
		long endTimeParallel = System.nanoTime();
		long durationParallel = (endTimeParallel - startTimeParallel) / 1_000_000;

		System.out.println("Tempo de execução (Stream): " + durationStream + " milissegundos");
		System.out.println("Tempo de execução (ParallelStream): " + durationParallel + " milissegundos");

		if (durationStream < durationParallel) {
			System.out.println("A abordagem com stream é mais rápida.");
		} else if (durationStream > durationParallel) {
			System.out.println("A abordagem paralela é mais rápida.");
		} else {
			System.out.println("Ambas as abordagens têm o mesmo desempenho.");
		}
	}

	private static void todasOcorrenciasDeUmEpisodio() {

		System.out.println("Digite o nome do episodio : ");
		String nome = sc.nextLine();

		List<Episodio> episodiosEncontrados = episodios.stream().filter(e -> e.getTitle().contains(nome.toUpperCase()))
				.collect(Collectors.toList());

		if (episodiosEncontrados.size() == 0) {
			System.out.println("Nenhum episodios encontrado");
		} else {
			episodiosEncontrados.forEach(System.out::println);
		}

	}

	private static void todasOcorrenciasDeUmEpisodioParalelizado() {

		System.out.println("Digite o nome do episodio : ");
		String nome = sc.nextLine();

		List<Episodio> episodiosEncontrados = episodios.parallelStream()
				.filter(e -> e.getTitle().contains(nome.toUpperCase()))
				.peek(e -> System.out.println("Buscando Paralelizados")).collect(Collectors.toList());

		if (episodiosEncontrados.size() == 0) {
			System.out.println("Nenhum episodios encontrado");
		} else {
			episodiosEncontrados.forEach(System.out::println);
		}

	}

	private static void primeiraOcorrenciaDeUmEpisodio() {

		System.out.println("Digite o nome do episodio : ");
		String nome = sc.nextLine();

		Optional<Episodio> episodio = episodios.stream().filter(e -> e.getTitle().contains(nome.toUpperCase()))
				.findFirst();
		if (episodio.isPresent()) {
			System.out.println(episodio.get());
		} else {
			System.out.println("Não foi encontrado");
		}
	}

	private static void primeiraOcorrenciaParalelizada() {
		System.out.println("Digite o nome do episodio : ");
		String nome = sc.nextLine();

		Optional<Episodio> episodio = episodios.parallelStream().filter(e -> e.getTitle().contains(nome.toUpperCase()))
				.findAny();
		if (episodio.isPresent()) {
			System.out.println(episodio.get());
		} else {
			System.out.println("Não foi encontrado");
		}
	}

	private static void TopEpisodios() {
		System.out.println("---TOP 5 MELHORES EPSODIOS---");
		top5MelhoresEpisodios().forEach(System.out::println);
	}

	private static void EpisodiosAPartirDoAno() {
		System.out.println("A partir de que ano você deseja ver os episodios ? ");

		Integer ano = Integer.parseInt(sc.nextLine());

		LocalDate dataBusca = LocalDate.of(ano, 1, 1);

		episodios.stream().filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
				.forEach(System.out::println);
		;
	}

	private static void buscaTemporadasNaAPI(String nomeSerie, int totalTemporadas) {
		IntStream.rangeClosed(1, totalTemporadas).forEach(i -> {
			String jsonTemporada = ConsumoApi
					.obterDados(ENDERECO + nomeSerie.trim().replaceAll("\\s+", "+") + "&season=" + i + API_KEY);
			DadosTemporada temporada = ConverterDados.obterDados(jsonTemporada, DadosTemporada.class);
			temporadas.add(temporada);
		});
	}

	private static List<Episodio> listaDeTodosEpisodios() {
		return temporadas.stream().flatMap(t -> t.episodios().stream().map(d -> new Episodio(t.numeroTemporada(), d)))
				.collect(Collectors.toList());
	}

	private static String buscaSerie(String nomeSerie) {
		return ConsumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
	}

	private static List<Episodio> top5MelhoresEpisodios() {
		return episodios.stream().sorted(Comparator.comparing(Episodio::getAvaliacao).reversed())
				.peek(e -> System.out.println("Ordenação")).limit(5).collect(Collectors.toList());
	}

}
