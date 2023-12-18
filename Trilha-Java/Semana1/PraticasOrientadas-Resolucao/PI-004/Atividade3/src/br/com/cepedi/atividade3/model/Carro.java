package br.com.cepedi.atividade3.model;

public class Carro extends Veiculo{
	
	int quantidadeDePortas;

	public Carro(String nome, String cor, int ano, int quantidadeDePortas) {
		super(nome, cor, ano);
		this.quantidadeDePortas = quantidadeDePortas;
	}
	
	@Override
	public void ligar() {
		System.out.println("Carro Ligando");
	}
	@Override
    public void acelerar() {
        System.out.println("Carro acelerando");
    }
	@Override
    public void parar() {
        System.out.println("Carro parado");
    }
	
    public String toString() {
        return "Carro " + super.toString() + "QuantidadeDePortas : " + this.quantidadeDePortas;
    }
	

}
