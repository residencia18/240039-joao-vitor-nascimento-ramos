package br.com.cepedi.controller.menus;

import java.util.Scanner;

import br.com.cepedi.exceptions.funcionario.SalarioFuncionarioInvalidoException;
import br.com.cepedi.exceptions.listaEmbarque.EmbarqueJaRegistrado;
import br.com.cepedi.exceptions.listaFuncionarios.FuncionarioNaoEncontrado;
import br.com.cepedi.exceptions.listaJornada.JornadaJaCadastrada;
import br.com.cepedi.exceptions.listaJornada.JornadaNaoEncontrada;
import br.com.cepedi.exceptions.listaJornadaTrajetoHorario.JornadaTrajetoHorarioException;
import br.com.cepedi.exceptions.listaPassageiros.PassageiroNaoEncontrado;
import br.com.cepedi.exceptions.listaPontosDeParada.PontoNaoEncontrado;
import br.com.cepedi.exceptions.listaTrajetos.TrajetoJaCadastrado;
import br.com.cepedi.exceptions.listaTrajetos.TrajetoNaoEncontrado;
import br.com.cepedi.exceptions.listaTrechos.TrechoJaCadastrado;
import br.com.cepedi.exceptions.listaTrechos.TrechoNaoEncontrado;
import br.com.cepedi.exceptions.listaVeiculos.VeiculoNaoEncontrado;
import br.com.cepedi.exceptions.motorista.CNHMotoristaInvalidaException;
import br.com.cepedi.exceptions.passageiro.CartaoInvalidoException;
import br.com.cepedi.exceptions.pessoa.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.pessoa.NomePessoaInvalidoException;
import br.com.cepedi.exceptions.veiculo.modeloVeiculoInvalidoException;
import br.com.cepedi.exceptions.veiculo.montadoraVeiculoInvalidaException;
import br.com.cepedi.exceptions.veiculo.placaVeiculoInvalidaException;
import br.com.cepedi.model.listas.ListaEmbarques;
import br.com.cepedi.model.listas.ListaFuncionarios;
import br.com.cepedi.model.listas.ListaJornadaTrajetoHorario;
import br.com.cepedi.model.listas.ListaJornadas;
import br.com.cepedi.model.listas.ListaPassageiros;
import br.com.cepedi.model.listas.ListaPontosDeParada;
import br.com.cepedi.model.listas.ListaTrajetos;
import br.com.cepedi.model.listas.ListaTrechos;
import br.com.cepedi.model.listas.ListaVeiculos;
import br.com.cepedi.persistenciaListasJSON.PersistenciaEmbarques;
import br.com.cepedi.persistenciaListasJSON.PersistenciaFuncionarios;
import br.com.cepedi.persistenciaListasJSON.PersistenciaJornadaTrajetoHorario;
import br.com.cepedi.persistenciaListasJSON.PersistenciaJornadas;
import br.com.cepedi.persistenciaListasJSON.PersistenciaPassageiros;
import br.com.cepedi.persistenciaListasJSON.PersistenciaPontosDeParada;
import br.com.cepedi.persistenciaListasJSON.PersistenciaTrajetos;
import br.com.cepedi.persistenciaListasJSON.PersistenciaTrechos;
import br.com.cepedi.persistenciaListasJSON.PersistenciaVeiculos;
import br.com.cepedi.view.MenuPrincipalView;

public abstract class MenuPrincipalController {
	

	
	public static void selecionarAcao(Scanner sc) {
		

		ListaVeiculos listaVeiculos = new ListaVeiculos();
		ListaPassageiros listaPassageiros = new ListaPassageiros();
		ListaFuncionarios listaFuncionarios = new ListaFuncionarios();
		ListaPontosDeParada listaPontosDeParada = new ListaPontosDeParada();
		ListaTrechos listaTrechos = new ListaTrechos();
		ListaTrajetos listaTrajetos = new ListaTrajetos();
		ListaJornadas listaJornadas = new ListaJornadas();
		ListaJornadaTrajetoHorario listaJornadaTrajetoHorario = new ListaJornadaTrajetoHorario();
		ListaEmbarques listaEmbarques = new ListaEmbarques();

		
		recuperaDados(listaVeiculos, listaPassageiros, listaFuncionarios, listaPontosDeParada, listaTrechos,
				listaTrajetos, listaJornadas, listaJornadaTrajetoHorario, listaEmbarques);
		
		int escolha;
		
		do {
			
			escolha = MenuPrincipalView.selecionaMenu(sc);
			
			switch(escolha) {
			case 1:
				MenuVeiculosController.selecionarAcao(sc, listaVeiculos);
				break;
			case 2:
				MenuPassageirosController.selecionarAcao(sc, listaPassageiros);
				break;
			case 3:
				MenuFuncionariosController.selecionarAcao(sc, listaFuncionarios);
				break;
			case 4:
				MenuPontosDeParadaController.selecionarAcao(sc, listaPontosDeParada);
				break;
			case 5:
				MenuTrechosController.selecionarAcao(sc, listaPontosDeParada , listaTrechos);
				break;
			case 6:
				MenuTrajetosController.selecionarAcao(sc, listaTrajetos, listaTrechos ,listaPontosDeParada);
				break;
			case 7:
				MenuJornadasController.selecionarAcao(sc, listaJornadas, listaFuncionarios, listaTrajetos , listaVeiculos,listaJornadaTrajetoHorario);
				break;
			case 8:
				MenuEmbarquesController.selecionarAcao(sc, listaPassageiros, listaJornadas, listaEmbarques);
				break;
			case 0:
				break;
				
			}
		}while(escolha!=0);
	}

	private static void recuperaDados(ListaVeiculos listaVeiculos, ListaPassageiros listaPassageiros,
			ListaFuncionarios listaFuncionarios, ListaPontosDeParada listaPontosDeParada, ListaTrechos listaTrechos,
			ListaTrajetos listaTrajetos, ListaJornadas listaJornadas,
			ListaJornadaTrajetoHorario listaJornadaTrajetoHorario, ListaEmbarques listaEmbarques) {
		try {
			PersistenciaVeiculos.lerArquivo(listaVeiculos);
		} catch (modeloVeiculoInvalidoException | montadoraVeiculoInvalidaException | placaVeiculoInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		
		try {
			PersistenciaPassageiros.lerArquivo(listaPassageiros);
		} catch (NomePessoaInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CPFPessoaInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CartaoInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		
		
		try {
			PersistenciaFuncionarios.lerArquivo(listaFuncionarios);
		} catch (modeloVeiculoInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (montadoraVeiculoInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (placaVeiculoInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NomePessoaInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CPFPessoaInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SalarioFuncionarioInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CNHMotoristaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		
		
		PersistenciaPontosDeParada.lerArquivo(listaPontosDeParada);
		
		try {
			PersistenciaTrechos.lerArquivo(listaTrechos, listaPontosDeParada);
		} catch (PontoNaoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		
		try {
			PersistenciaTrajetos.lerArquivo(listaTrajetos, listaTrechos, listaPontosDeParada);
		} catch (PontoNaoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TrechoJaCadastrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TrajetoJaCadastrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		
		try {
			PersistenciaJornadas.lerArquivo(listaJornadas, listaTrechos, listaPontosDeParada, listaVeiculos, listaFuncionarios, listaTrajetos);
		} catch (VeiculoNaoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FuncionarioNaoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JornadaJaCadastrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TrajetoJaCadastrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TrajetoNaoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e ) {
			e.printStackTrace();
		}
		
		
		try {
			PersistenciaJornadaTrajetoHorario.lerArquivo(listaJornadaTrajetoHorario, listaJornadas, listaTrajetos);
		} catch (TrajetoNaoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JornadaNaoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JornadaTrajetoHorarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e ) {
			e.printStackTrace();
		}

		
		
		try {
			PersistenciaEmbarques.lerArquivo(listaEmbarques, listaPassageiros, listaJornadas, listaTrajetos, listaTrechos, listaPontosDeParada);
		} catch (JornadaNaoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PassageiroNaoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TrajetoNaoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TrechoNaoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PontoNaoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmbarqueJaRegistrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e ) {
			e.printStackTrace();
		}
	}

}
