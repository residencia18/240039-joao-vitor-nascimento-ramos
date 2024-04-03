package br.com.alura.screenmatch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.principal.Principal;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverterDados;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Principal.imprimirMenu();
		
//		String json = ConsumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
//		json = ConsumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
//		System.out.println(json);
//		System.out.println(json);
//		
//		DadosSerie dados = ConverterDados.obterDados(json, DadosSerie.class);
//		System.out.println(dados);
//		
//		json = ConsumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=6585022c");
//		DadosEpisodio dadosEpisodio = ConverterDados.obterDados(json, DadosEpisodio.class);
//		System.out.println(dadosEpisodio);
//		
//		List<DadosTemporada> temporadas = new ArrayList<>();
//		
//		for(int i = 1 ; i <=dados.totalTemporadas() ; i++) {
//            json = ConsumoApi.obterDados ("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=6585022c");
//			DadosTemporada temporada = ConverterDados.obterDados(json, DadosTemporada.class);
//			temporadas.add(temporada);
//		}
//		
//		temporadas.forEach(System.out::println);
	}

}
