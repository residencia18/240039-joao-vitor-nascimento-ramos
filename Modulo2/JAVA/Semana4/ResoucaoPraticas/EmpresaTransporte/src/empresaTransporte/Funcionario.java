package empresaTransporte;

public abstract class Funcionario extends Pessoa{

	private double salario;

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Funcionario(String nome, String cpf, int idade, String rG, double salario) {
		super(nome, cpf, idade, rG);
		this.salario = salario;
	}
	
	
	

}
