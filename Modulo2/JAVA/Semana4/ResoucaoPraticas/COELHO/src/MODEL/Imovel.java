package MODEL;

import java.util.ArrayList;

public class Imovel {
	private int id;
    private String matricula;
    private String endereco;
    private Leitura ultimaLeitura;
    private Leitura penultimaLeitura;
    private ArrayList<Leitura> leituras;
    private ArrayList<Fatura> faturas;
    Cliente cliente;

    public Imovel() {
    	this.ultimaLeitura = new Leitura();
		this.penultimaLeitura = new Leitura();
    	leituras = new ArrayList<>();
		faturas = new ArrayList<>();
	}
    
	public Imovel(String matricula, String endereco, Cliente cliente) {
		this.matricula = matricula;
		this.endereco = endereco;
		this.ultimaLeitura = new Leitura();
		this.penultimaLeitura = new Leitura();
		this.cliente = cliente;
		leituras = new ArrayList<>();
		faturas = new ArrayList<>();
	}

	public ArrayList<Fatura> getFaturas() {
		return faturas;
	}
	
	public void addLeitura(Leitura leitura) {
		
		this.penultimaLeitura.setLeitura(ultimaLeitura.getLeitura());
		this.ultimaLeitura.setLeitura(leitura.getLeitura());
				
		this.leituras.add(leitura);
	}
	
	public ArrayList<Leitura> getLeitura() {
		return leituras;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getUltimaLeitura() {
		if (ultimaLeitura == null) {
			return 0;
		}
		return ultimaLeitura.getLeitura();
	}
	public void setUltimaLeitura(int ultimaLeitura) {
		this.ultimaLeitura.setLeitura(ultimaLeitura);
	}
	public int getPenultimaLeitura() {
		if (penultimaLeitura == null) {
			return 0;
		}
		return penultimaLeitura.getLeitura();
	}
	public void setPenultimaLeitura(int penultimaLeitura) {
		this.penultimaLeitura.setLeitura(penultimaLeitura);
	}
    
}
