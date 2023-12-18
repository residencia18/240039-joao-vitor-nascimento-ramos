package br.com.cepedi.atividade3.model;

public class Moto extends Veiculo{
	
	private int cilindradas;

	public Moto(String nome, String cor, int ano , int cilindradas, boolean eletrico) {
		super(nome, cor, ano, eletrico);
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

    @Override
    public void estacionar(Garagem garagem) {
    	garagem.adicionarVeiculo(this);
    	System.out.println("Moto estacionada!");
    	if(garagem.tomadaEletrica && this.eletrico) {
    		System.out.println("Moto carregando");
    	}
    }
    
}
