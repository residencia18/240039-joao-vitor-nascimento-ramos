package br.com.cepedi.atividade3.model;

public abstract class Veiculo {
	
	protected String modelo;
	protected String cor;
	protected int ano;
	boolean eletrico;
	
	
	public Veiculo(String nome, String cor, int ano, boolean eletrico) {
		super();
		this.modelo = nome;
		this.cor = cor;
		this.ano = ano;
		this.eletrico = eletrico;
	}
	
	public boolean isEletrico() {
		return eletrico;
	}

	public void setEletrico(boolean eletrico) {
		this.eletrico = eletrico;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getModelo() {
		return modelo;
	}
	
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
    public void ligar() {
        System.out.println("Veículo ligado");
    }

    public void acelerar() {
        System.out.println("Veículo acelerando");
    }

    public void parar() {
        System.out.println("Veículo parado");
    }
    

    
    @Override
	public String toString() {
		return "Veiculo [modelo=" + modelo + ", cor=" + cor + ", ano=" + ano + ", eletrico=" + eletrico + "]";
	}

	public void estacionar(Garagem garagem) {
    	garagem.adicionarVeiculo(this);
    	System.out.println("Veículo estacionado!");
    	if(garagem.tomadaEletrica && this.eletrico) {
    		System.out.println("Veículo carregando");
    	}
    }
    

}
