package br.com.cepedi.atividade3.model;

public abstract class Veiculo {
	
	protected String modelo;
	protected String cor;
	protected int ano;
	
	
	
	public Veiculo(String nome, String cor, int ano) {
		super();
		this.modelo = nome;
		this.cor = cor;
		this.ano = ano;
	}
	
	public String getModelo() {
		return modelo;
	}
	public void setModeo(String modelo) {
		this.modelo = modelo;
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
        return "[modelo=" + modelo + ", cor=" + cor + ", ano=" + ano + "]";
    }
    

}
