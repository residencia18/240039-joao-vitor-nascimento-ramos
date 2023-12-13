package empresaTransporte;

import java.util.ArrayList;

public class Trajeto {
	
	private static int numeroDeTrajetos;
	
	private int id;
    private ArrayList<Trecho> listaDeTrechos = new ArrayList<>();
    private int tempoTotalEstimado; // Soma dos tempos de cada trechos
    private PontoDeParada checkpoint;
    
    public Trajeto(ArrayList<Trecho> listaDeTrechos,PontoDeParada checkpoint) {
    	numeroDeTrajetos++;
    	id = numeroDeTrajetos;
    	this.listaDeTrechos = listaDeTrechos;
		this.checkpoint = checkpoint;
		// tempo total é dado pela soma de todos os tempo previos por cada trecho ( pode ser feito isso numa função)
	}

	public PontoDeParada getCheckpoint() {
		return checkpoint;
	}

	public ArrayList<Trecho> getListaDeTrechos() {
		return listaDeTrechos;
	}

	public static int getNumeroDeTrajetos() {
		return numeroDeTrajetos;
	}

	public static void setNumeroDeTrajetos(int numeroDeTrajetos) {
		Trajeto.numeroDeTrajetos = numeroDeTrajetos;
	}

	public int getId() {
		return id;
	}

	public int getTempoTotalEstimado() {
		return tempoTotalEstimado;
	}


	public void setCheckpoint(PontoDeParada checkpoint) {
		//necesita verificar se o PontoDeParada está presente na lita de trechos
		this.checkpoint = checkpoint;
	}

	

}
