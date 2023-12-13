package empresaTransporte;

public class Cobrador extends Funcionario{

	private static int numeroDeCobradores;
	
	private int id;
	
	public Cobrador(String nome, String cpf, int idade, String rG, double salario) {
		numeroDeCobradores++;
		id = numeroDeCobradores;
		super(nome, cpf, idade, rG, salario);
	}
	
	
}
