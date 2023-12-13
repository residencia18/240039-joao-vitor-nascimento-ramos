package empresaTransporte;

public class Passageiro extends Pessoa {
	
	private static int numeroDePasageiros;
	
	private int id;
	
	private CartaoPassagem cartao;

	public Passageiro(String nome, String cpf, int idade, String rG, CartaoPassagem cartao) {
		numeroDePasageiros++;
		id = numeroDePasageiros;
		super(nome, cpf, idade, rG);
		this.cartao = cartao;
	}
	
}
