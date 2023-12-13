package MODEL;

public class Leitura {
	private int id;
	private int leitura;
	private int id_imovel;
	
	public Leitura(int leitura, int id_imovel) {
		this.leitura = leitura;
		this.id_imovel = id_imovel;
	}
	
	public Leitura() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLeitura() {
		return leitura;
	}

	public void setLeitura(int leitura) {
		this.leitura = leitura;
	}

	public int getId_imovel() {
		return id_imovel;
	}

	public void setId_imovel(int id_imovel) {
		this.id_imovel = id_imovel;
	}
	
	
}
