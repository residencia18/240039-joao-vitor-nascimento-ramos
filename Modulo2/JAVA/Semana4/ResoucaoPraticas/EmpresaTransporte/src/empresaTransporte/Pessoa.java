package empresaTransporte;

public abstract class Pessoa {
	
	private String nome;
	private String cpf;
	private int idade;
	private String RG;
	
	
	
	
	public Pessoa(String nome, String cpf, int idade, String rG) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		RG = rG;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getRG() {
		return RG;
	}
	public void setRG(String rG) {
		RG = rG;
	}
	
	

}
