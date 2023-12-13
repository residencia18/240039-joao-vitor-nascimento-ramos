package empresaTransporte;

public class Motorista extends Funcionario{
	
	private static int numeroDeMotoristas;
	
	private int id;
	private String numeroCNH;
	
	
	public Motorista(String nome, String cpf, int idade, String rG, double salario, String numeroCNH) {
		numeroDeMotoristas++;
		id = numeroDeMotoristas;
		super(nome, cpf, idade, rG, salario);
		this.numeroCNH = numeroCNH;
	}
	
	
	
}
