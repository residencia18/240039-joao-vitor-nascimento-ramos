package MODEL;

import java.util.ArrayList;

public class Cliente {
	private int id;
    private String nome;
    private String cpf;
    ArrayList<Imovel> imoveisCliente;
        
    
	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		this.imoveisCliente = new ArrayList<>();
	}
	
	public Cliente() {
		this.imoveisCliente = new ArrayList<>();
	}
	
    public void adicionarImovel(Imovel imovel) {
        imoveisCliente.add(imovel);
    }

    public void removerImovel(Imovel imovel) {
        imoveisCliente.remove(imovel);
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public ArrayList<Imovel> getImoveisCliente() {
		return imoveisCliente;
	}

	public void setImoveisCliente(ArrayList<Imovel> imoveisCliente) {
		this.imoveisCliente = imoveisCliente;
	}
    
	
	
	
}
