package br.com.cepedi.controler.menus;

import java.math.BigDecimal;
import java.util.Scanner;

import br.com.cepedi.exceptions.cartaoPassagem.SaldoInsuficienteException;
import br.com.cepedi.model.listas.ListaEmbarques;
import br.com.cepedi.model.listas.ListaJornadas;
import br.com.cepedi.model.listas.ListaPassageiros;
import br.com.cepedi.model.pessoa.Passageiro;
import br.com.cepedi.model.transporte.Embarque;
import br.com.cepedi.model.transporte.Jornada;
import br.com.cepedi.model.transporte.PontoDeParada;
import br.com.cepedi.model.transporte.Trajeto;
import br.com.cepedi.model.transporte.Trecho;
import br.com.cepedi.view.MenuEmbarqueView;

public abstract class MenuEmbarquesController {
	
	private final static BigDecimal VALOR_PASSAGEM = new BigDecimal("5.0");
	
	
	public static void selecionarAcao(Scanner sc, ListaPassageiros passageiros , ListaJornadas jornadas , ListaEmbarques embarques) {

		int escolha;
		
		do {
			escolha = MenuEmbarqueView.selecionaAcao(sc);
			
			switch(escolha) {
			case 1:
				embarca(sc,passageiros,jornadas,embarques);
				break;
			case 2:
				mostraPorPassageiro(sc,passageiros,embarques);
				break;
			case 3:
				mostraTodos(embarques);
				break;
			case 0:
				break;
			
			}
		}while(escolha!=0);
	}
	
	private static void embarca(Scanner sc, ListaPassageiros passageiros , ListaJornadas jornadas ,  ListaEmbarques embarques) {
		
		Passageiro passageiro = null;
		passageiro = recebePassageiro(sc, passageiros);
		if(passageiro==null) {
			return;
		}
		
		try {
			passageiro.getCartao().usarPassagem(VALOR_PASSAGEM);
		} catch (SaldoInsuficienteException e) {
			System.out.println(e.getMessage());
			return;
		}
		
		Jornada jornada = null;
		jornada = recebeJornada(sc,jornadas);
		if(jornada==null) {
			return;
		}
		
		Trajeto trajeto = null;
		trajeto = recebeTrajeto(sc,jornada);
		if(trajeto==null) {
			return;
		}
		
		Trecho trecho = null;
		trecho = recebeTrecho(sc,trajeto);
		if(trecho==null) {
			return;
		}
		
		PontoDeParada ponto = null;
		ponto = recebePonto(sc,trecho);
		if(ponto==null) {
			return;
		}
		
		try {
			Embarque embarque = new Embarque(passageiro,jornada,trajeto,trecho,ponto);
			embarques.adiciona(embarque);
		}catch(Exception e ) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	private static void mostraPorPassageiro(Scanner sc , ListaPassageiros passageiros , ListaEmbarques embarques) {
		Passageiro passageiro = null;
		int qtdEmbarques = 0;
		passageiro = recebePassageiro(sc, passageiros);
		if(passageiro==null) {
			return;
		}
		
		if(embarques.isEmpty()) {
			System.out.println("Não há embarques registrados");
			return;
		}
		
		
		for(Embarque e : embarques) {
			if(e.getPassageiro().equals(passageiro)) {
				System.out.println(e);
				qtdEmbarques++;
			}
		}
		
		if(qtdEmbarques==0) {
			System.out.println(passageiro.getNome() + " não possui embarques realizados");
		}
	}
	
	private static void mostraTodos(ListaEmbarques embarques) {
		if(embarques.isEmpty()) {
			System.out.println("Não há embarques registrados");
			return;
		}
		
		for(Embarque e : embarques) {
			System.out.println(e);
		}
	}
	
	
	
	private static PontoDeParada recebePonto(Scanner sc , Trecho trecho) {
		PontoDeParada ponto;
		String escolhaContinue;
		String nomePonto;
		
		do {
			try {
				System.out.println("Digite o nome do ponto");
				nomePonto = sc.nextLine();
				if(nomePonto.equals(trecho.getOrigem().getNome())) {
					ponto = trecho.getOrigem();
				}else if(nomePonto.equals(trecho.getDestino().getNome())) {
					ponto = trecho.getDestino();
				}else {
					throw new IllegalArgumentException("Não foi possivel encontrar o ponto ");
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Deseja continuar ? (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return null;
				}
				continue;	
			}
			break;
		}while(true);
		
		return ponto;
	}
	
	private static Trecho recebeTrecho(Scanner sc , Trajeto trajeto) {
		Trecho trecho;
		String escolhaContinue;
		String nomeOrigem, nomeDestino;
		do {
			try {
				System.out.println("Digite o nome do ponto de origem do trecho");
				nomeOrigem = sc.nextLine();
				System.out.println("Digite o nome do ponto de destino do trecho");
				nomeDestino = sc.nextLine();
				trecho = trajeto.getTrechos().buscar(nomeOrigem, nomeDestino);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Deseja continuar ? (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return null;
				}
				continue;	
			}
			break;
		}while(true);
		return trecho;
	}
	
	private static Trajeto recebeTrajeto(Scanner sc , Jornada jornada) {
		Trajeto trajeto;
		String escolhaContinue;
		int idTrajeto;
		do {
			try {
				System.out.println("Digite o id do trajeto dessa jornada");
				idTrajeto = Integer.parseInt(sc.nextLine());
				trajeto = jornada.getTrajetos().buscar(idTrajeto);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Deseja continuar ? (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return null;
				}
				continue;	
			}
			break;
		}while(true);
		return trajeto;
	}
	
	private static Jornada recebeJornada(Scanner sc , ListaJornadas jornadas) {
		Jornada jornada;
		String escolhaContinue;
		int idJornada;
		do {
			try {
				System.out.println("Digite o id da jornada");
				idJornada = Integer.parseInt(sc.nextLine());
				jornada = jornadas.buscar(idJornada);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Deseja continuar ? (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return null;
				}
				continue;	
			}
			break;
		}while(true);
		return jornada;
	}

	private static Passageiro recebePassageiro(Scanner sc, ListaPassageiros passageiros) {
		Passageiro passageiro;
		String escolhaContinue;

		
		do {
			try {
				System.out.println("Digite o CPF do passageiro");
				passageiro = passageiros.buscar(sc.nextLine());
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Deseja continuar ? (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return null;
				}
				continue;	
			}
			break;
		}while(true);
		return passageiro;
	}
	

}
