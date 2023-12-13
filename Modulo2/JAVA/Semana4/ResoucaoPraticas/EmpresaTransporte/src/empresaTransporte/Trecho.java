package empresaTransporte;

public class Trecho {
	
	private static int numeroDeTrechos;
	
	private int id;
	private PontoDeParada origem;
	private PontoDeParada destino;
	private int duracaoEstimada; // em minutos
	
	
	public int getId() {
		return id;
	}

	
	public PontoDeParada getOrigem() {
		return origem;
	}
	public void setOrigem(PontoDeParada origem) {
		this.origem = origem;
	}
	public PontoDeParada getDestino() {
		return destino;
	}
	public void setDestino(PontoDeParada destino) {
		this.destino = destino;
	}
	public int getDuracaoEstimada() {
		return duracaoEstimada;
	}
	public void setDuracaoEstimada(int duracaoEstimada) {
		this.duracaoEstimada = duracaoEstimada;
	}
	
	
	

	

}
