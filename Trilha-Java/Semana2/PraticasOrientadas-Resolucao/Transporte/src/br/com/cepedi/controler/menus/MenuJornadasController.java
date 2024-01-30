package br.com.cepedi.controler.menus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.cepedi.exceptions.listaFuncionarios.FuncionarioNaoEncontrado;
import br.com.cepedi.exceptions.listaJornadaTrajetoHorario.JornadaTrajetoHorarioException;
import br.com.cepedi.exceptions.listaTrajetos.TrajetoJaCadastrado;
import br.com.cepedi.exceptions.listaTrajetos.TrajetoNaoEncontrado;
import br.com.cepedi.exceptions.listaVeiculos.VeiculoNaoEncontrado;
import br.com.cepedi.model.listas.ListaFuncionarios;
import br.com.cepedi.model.listas.ListaJornadaTrajetoHorario;
import br.com.cepedi.model.listas.ListaJornadas;
import br.com.cepedi.model.listas.ListaTrajetos;
import br.com.cepedi.model.listas.ListaVeiculos;
import br.com.cepedi.model.pessoa.Cobrador;
import br.com.cepedi.model.pessoa.Motorista;
import br.com.cepedi.model.transporte.Jornada;
import br.com.cepedi.model.transporte.JornadaTrajetoHorario;
import br.com.cepedi.model.transporte.Trajeto;
import br.com.cepedi.model.veiculo.Veiculo;
import br.com.cepedi.view.MenuJornadasView;

public abstract class MenuJornadasController {
	
	public static void selecionarAcao(Scanner sc, ListaJornadas jornadas , ListaFuncionarios funcionarios ,
			ListaTrajetos trajetos ,  ListaVeiculos veiculos , ListaJornadaTrajetoHorario listaJornadaTrajetoHorario) {

		int escolha;
		
		do {
			escolha = MenuJornadasView.selecionaAcao(sc);
			
			switch(escolha) {
			case 1:
				cadastra(sc,jornadas,funcionarios,trajetos, veiculos , listaJornadaTrajetoHorario);
				break;
			case 2:
				mostra(sc,jornadas);
				break;
			case 3:
				listaTodos(jornadas);
				break;
			case 4:
				exclui(sc,jornadas);
				break;
			case 5:
				mostraHorariosTrajetos(listaJornadaTrajetoHorario);
				break;
			case 6:
				mostrarHorariosPorJornada(sc, listaJornadaTrajetoHorario , jornadas);
				break;
			case 0:
				break;
			
			}
		}while(escolha!=0);
	}
	
	private static void cadastra(Scanner sc, ListaJornadas jornadas , ListaFuncionarios funcionarios , ListaTrajetos trajetos
			, ListaVeiculos veiculos , ListaJornadaTrajetoHorario listaJornadaTrajetoHorario) {
		
	    String escolhaContinue = "";
	    Jornada jornada = null;


	    do {
	        try {
	        	jornada = instanciaJornada(sc, funcionarios, veiculos);
	    	    adicionaTrajeto(sc, trajetos, jornada);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            System.out.println("Deseja tentar a busca novamente (0 - para parar, qualquer outra tecla para continuar) ");
	            escolhaContinue = sc.nextLine();
	            if (escolhaContinue.equals("0")) {
	                return;
	            }
	            continue;
	        }
	        break;
	    } while (true);

	    preencheTrajetos(sc, jornadas, trajetos, jornada);
	    try {	    	
	    	armazenaHorarios(jornada,listaJornadaTrajetoHorario);
	    }catch(Exception e) {
	    	System.out.println(e.getMessage());
	    	System.out.println("Não foi possivel armazenar os horarios de cada trajeto");
	    }
		
	}

	private static void preencheTrajetos(Scanner sc, ListaJornadas jornadas, ListaTrajetos trajetos, Jornada jornada) {
		String escolhaContinue;
		do {
	        System.out.println("Deseja inserir um novo trajeto? (0 - para parar, qualquer outra tecla para continuar)  ");
	        escolhaContinue = sc.nextLine();
	        if (escolhaContinue.equals("0")) {
	            try {
	            	jornadas.adiciona(jornada);
	            	System.out.println("Jornada adicionada com sucesso!");
	                break;
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        } else {
	            try {
		    	    adicionaTrajeto(sc, trajetos, jornada);
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	                System.out.println("Deseja tentar a busca novamente (0 - para parar, qualquer outra tecla para continuar) ");
	                escolhaContinue = sc.nextLine();
	                if (escolhaContinue.equals("0")) {
	                    return;
	                }
	                continue;
	            }
	        }
	    } while (true);
	}

	private static void adicionaTrajeto(Scanner sc, ListaTrajetos trajetos, Jornada jornada)
			throws TrajetoNaoEncontrado, TrajetoJaCadastrado {
		System.out.println(jornada);
		Trajeto trajeto = null;
		int idTrajeto;
		System.out.println("Digite o id de um trajeto de sua jornada ");
		idTrajeto = Integer.parseInt(sc.nextLine());
		trajeto = trajetos.buscar(idTrajeto);
		jornada.getTrajetos().adiciona(trajeto);
	}

	private static Jornada instanciaJornada(Scanner sc, ListaFuncionarios funcionarios, ListaVeiculos veiculos)
			throws VeiculoNaoEncontrado, FuncionarioNaoEncontrado {
		
		Jornada jornada;
		String escolhaCobrador= "";
		Motorista motorista;
		Cobrador cobrador;
		String nome;
		Veiculo veiculo;
		LocalDateTime dataHoraInicio = null;
		String escolhaContinue = "";

		dataHoraInicio = recebeDataHoraJornada(sc);
		if(dataHoraInicio==null) {
			return null;
		}
		
		
		nome = recebeNomeJornada(sc);
		if(nome==null) {
			return null;
		}

		veiculo = recebeVeiculoJornada(sc, veiculos);
		if(veiculo==null) {
			return null;
		}
	
		motorista = recebeMotoristaJornada(sc, funcionarios);
		if(motorista==null) {
			return null;
		}
	    System.out.println("Jornada criada com sucesso!");
	    jornada = new Jornada(veiculo,motorista,nome,dataHoraInicio);
		escolhaCobrador = decideSeTemCobrador(sc);
		
		
	    // if escolhaCobrador for igual a 0 quer q tenha cobrador
		if(!escolhaCobrador.equals("0")) {
			cobrador = recebeCobradorJornada(sc, funcionarios);
		    jornada.setCobrador(cobrador);
		}
		return jornada;
	}

	private static Cobrador recebeCobradorJornada(Scanner sc, ListaFuncionarios funcionarios)
			throws FuncionarioNaoEncontrado {
		String escolhaContinue;
		Cobrador cobrador;
		do {
			try {
				System.out.println("Digite o cpf do cobrador responsavel pela jornada");
				cobrador = (Cobrador) funcionarios.buscar(sc.nextLine());			
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Deseja continua? (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return null;
				}
				continue;	
			}
			break;
		}while(true);

		return cobrador;
	}

	private static String decideSeTemCobrador(Scanner sc) {
		String escolhaCobrador;
		System.out.println("Deseja inserir um cobrador ? ( 0 para não, qualquer outra tecla para sim)");
		escolhaCobrador = sc.nextLine();
		return escolhaCobrador;
	}

	private static Motorista recebeMotoristaJornada(Scanner sc, ListaFuncionarios funcionarios)
			throws FuncionarioNaoEncontrado {
		String escolhaContinue;
		Motorista motorista;
		do {
			try {
				System.out.println("Digite o cpf do motorista responsavel pela jornada");
				motorista = (Motorista) funcionarios.buscar(sc.nextLine());
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Deseja continua? (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return null;
				}
				continue;	
			}
			break;
		}while(true);
		
		return motorista;
	}

	private static Veiculo recebeVeiculoJornada(Scanner sc, ListaVeiculos veiculos) throws VeiculoNaoEncontrado {
		String escolhaContinue;
		Veiculo veiculo;
		do {
			try {
				System.out.println("Digite a placa do veiculo");
				veiculo = veiculos.buscar(sc.nextLine());
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Deseja continua? (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return null;
				}
				continue;	
			}
			break;
		}while(true);

		return veiculo;
	}

	private static String recebeNomeJornada(Scanner sc) {
		String nome = null;
		String escolhaContinue;
		do {
			try {
				System.out.println("Digite um nome para jornada");
				nome = sc.nextLine();
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Deseja continua? (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return null;
				}
				continue;
			}
			
			break;
		}while(true);
		return nome;
	}

	private static LocalDateTime recebeDataHoraJornada(Scanner sc) {
		LocalDateTime dataHoraInicio = null;
		String inputDateTime;
		String escolhaContinue;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		do {
			try {
				System.out.println("Digite a data de início da jornada (dd/MM/yyyy HH:mm):");
				inputDateTime = sc.nextLine();
				dataHoraInicio = LocalDateTime.parse(inputDateTime, formatter);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Deseja continua? (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return null;
				}
				continue;
			}
			
			break;
		}while(true);
		return dataHoraInicio;
	}
	
	private static void mostra(Scanner sc , ListaJornadas jornadas) {
		Jornada jornada;
		String escolhaContinue = "";
		int idJornada;
				
		do {
			try {
				System.out.println("Digite o id da jornada que deseja buscar");
				idJornada = Integer.parseInt(sc.nextLine());
				jornada = jornadas.buscar(idJornada);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar a busca novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			
			System.out.println(jornada);
			break;
		}while(true);

	}
	
	private static void listaTodos(ListaJornadas jornadas) {
		jornadas.mostraTodos();
	}
	
	private static void exclui(Scanner sc , ListaJornadas jornadas) {
		String escolhaContinue = "";
		int idJornada;
				
		do {
			try {
				System.out.println("Digite o id do trajeto que deseja excluir");
				idJornada = Integer.parseInt(sc.nextLine());
				jornadas.deletar(idJornada);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar a busca novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			
			break;
		}while(true);
	}
	
	private static void armazenaHorarios(Jornada jornada , ListaJornadaTrajetoHorario listaJornadaTrajetoHorario) throws JornadaTrajetoHorarioException {
		int minutosPassados = 0;
		LocalDateTime horarioInicio = jornada.getDataInicio();
		
		for(Trajeto trajeto : jornada.getTrajetos()) {
			minutosPassados += trajeto.tempoDeTodosTrechos();
			JornadaTrajetoHorario jth = new JornadaTrajetoHorario(jornada,trajeto,horarioInicio.plusMinutes(minutosPassados));
			listaJornadaTrajetoHorario.adiciona(jth);
		}
	}
	
	private static void mostraHorariosTrajetos(ListaJornadaTrajetoHorario listaJornadaTrajetoHorario) {
		listaJornadaTrajetoHorario.mostraTodos();
	}
	
	private static void mostrarHorariosPorJornada(Scanner sc , ListaJornadaTrajetoHorario listaJornadaTrajetoHorario , ListaJornadas jornadas) {
		int idJornada;
		Jornada jornada;
		
		try {
			System.out.println("Digite o id da jornada");
			idJornada = Integer.parseInt(sc.nextLine());
			jornada = jornadas.buscar(idJornada);
			listaJornadaTrajetoHorario.mostrarPorJornada(jornada);
		}catch(Exception e ) {
			System.out.println(e.getMessage());
		}

	}

	
	
	
	
	
	
	
	
	
	
	
}
