package br.com.cepedi.atividade3.App;

import br.com.cepedi.atividade3.model.Carro;
import br.com.cepedi.atividade3.model.Garagem;
import br.com.cepedi.atividade3.model.Moto;

public class App {
	
	public static void main(String[] args) {
        Carro carro = new Carro("Civic", "Prata", 2023,4,true);
        Moto moto = new Moto("Harley", "Vermelho", 2021,160,false);

        carro.ligar();
        carro.acelerar();
        carro.parar();
        System.out.println(carro);

        System.out.println();

        moto.ligar();
        moto.acelerar();
        moto.parar();
        System.out.println(moto);
        

        Garagem garagem = new Garagem(true);

        carro.estacionar(garagem);
        moto.estacionar(garagem);
    

	}

}
