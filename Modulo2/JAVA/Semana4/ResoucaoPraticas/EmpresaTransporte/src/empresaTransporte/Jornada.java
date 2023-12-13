package empresaTransporte;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Jornada {

	private static int numeroDeJornadas;
	
	private int id;
	private Trajeto trajeto;
	private LocalDateTime horarioInicioJornada;	
    private ArrayList<LocalDateTime> horarioFimDeCadaTrajeto = new ArrayList<>(); // o fim do horario de um já é o inicio do horario do outro . A quantidade de trajetos vai ser medida pelo tamanho da lita
    private boolean finalizada = false;
    private List<Map<Passageiro, LocalDateTime>> listaPassageiros = new ArrayList<>(); // passageiro e o horario que embarcou
    Cobrador cobrador;
    Motorista motorista;
    Veiculo veiculo;
    private PontoDeParada pontoAtual;
    private double dinheiroEmCaixa; // armazena o valor que ficou de dinheiro no onibus no fim da jornada


    /* solicita o cartão do passageiro e apartir disso identifica o passageiro na lista dos cadastrados
     * verifica se possui saldo disponivel
     * insere na lista de litaPassageiros instanciando um localDateTime para armazenar ua entrada no onibus
    */
    public void entradaDePassageiro(ArrayList<Passageiro> passageirosCadastrados) {
    	
    }
    
    private boolean verificaChegadaCheckpoint() {
    	// Verifica se o pontoAtual é igual ao checkpoint, caso seja retorna true
    	return (pontoAtual == trajeto.getCheckpoint()); 
    }
    
    // atualiza o ponto de parada pela lista de trechos do trajeto
    public void atualizaPontoParada() {
    	 //atualiza o ponto atual
    	// depoi verifica a chegada
    	if(verificaChegadaCheckpoint()) {
            horarioFimDeCadaTrajeto.add(LocalDateTime.now());
    	}
    }

	public Jornada(Trajeto trajeto, Cobrador cobrador, Motorista motorista, Veiculo veiculo) {
		numeroDeJornadas++;
		this.id = numeroDeJornadas;
		this.trajeto = trajeto;
		this.cobrador = cobrador;
		this.motorista = motorista;
		this.veiculo = veiculo;
        horarioInicioJornada = LocalDateTime.now();
        this.pontoAtual = trajeto.getListaDeTrechos().get(0).getOrigem();
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}
    
    
    
    
    

}

