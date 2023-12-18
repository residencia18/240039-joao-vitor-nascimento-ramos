package br.com.cepedi.atividade3.model;

public class Moto extends Veiculo{
	
	private int cilindradas;

	public Moto(String nome, String cor, int ano , int cilindradas) {
		super(nome, cor, ano);
		this.cilindradas = cilindradas;
	}
	
	@Override
	public void ligar() {
		System.out.println("Moto Ligando");
	}
	@Override
    public void acelerar() {
        System.out.println("Moto acelerando");
    }
	@Override
    public void parar() {
        System.out.println("Moto parado");
    }
	
    public String toString() {
        return "Moto " + super.toString() + "Cilindradas : " + this.cilindradas;
    }

}
