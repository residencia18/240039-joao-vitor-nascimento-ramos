package br.com.cepedi;

import java.util.ArrayList;
import java.util.List;

import br.com.dominio.Barco;
import br.com.dominio.Carro;
import br.com.service.RentalService;

public class GenericsTest {
	
	public static void main(String[] args) {
		List<Carro> carrosDisponiveis = new ArrayList<>(List.of(new Carro("BMW") , new Carro("Fusca")));
		List<Barco> barocsDisponiveis = new ArrayList<>(List.of(new Barco("Navio") , new Barco("Canoa")));
		RentalService<Barco> rentalService = new RentalService<>(barocsDisponiveis);
		Barco carro = rentalService.buscarObjetoDisponivel();
		rentalService.retornarCarroAlugado(carro);
	}

}
