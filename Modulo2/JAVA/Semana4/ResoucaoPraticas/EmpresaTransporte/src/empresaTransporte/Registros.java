package empresaTransporte;

import java.util.ArrayList;


public class Registros {
	
	private ArrayList<Passageiro> passageiros = new ArrayList<>();
	private ArrayList<Motorista> motoristas = new ArrayList<>();
	private ArrayList<Cobrador> cobradores = new ArrayList<>();
	private ArrayList<PontoDeParada> pontos = new ArrayList<>();
	private ArrayList<Trecho> trechos = new ArrayList<>();
	private ArrayList<Trajeto> trajetos = new ArrayList<>(); // lista de trajetos prontos
	private ArrayList<Jornada> jornadas = new ArrayList<>();

	
	// Método responsável por iniciar uma jornada, chamando a função Cadastro.cadastraJornada e depois inserir ela no List jornadas
	public static void IniciaJornada() {
		
	} 
	
	//altera o atributo Boolean da Jornada para false;
	public static void finalizaJornada(Jornada jornada) {
		jornada.setFinalizada(true);
		//abre uma caixa de dialogo para que o reponável insira o valor que ficou no caixa no fim do dia ( dos passageiros q não posuem cartão)
	
	} 
	
	public void insereNovoPassageiro() {
		//chama da função static de cadastra e caso passe insere em passageiros
	}
	
	public void insereNovoMotorista() {
		//chama da função static de cadastra e caso passe insere em passageiros
	}
	
	public void insereNovoCobrador() {
		//chama da função static de cadastra e caso passe insere em passageiros
	}
	
	public void insereNovoPonto() {
		//chama da função static de cadastra e caso passe insere em passageiros
	}
	
	public void insereNovoTrecho() {
		//chama da função static de cadastra e caso passe insere em passageiros
	}
	
	public void insereNovoTrajeto() {
		//chama da função static de cadastra e caso passe insere em passageiros
	}
	
	

}
