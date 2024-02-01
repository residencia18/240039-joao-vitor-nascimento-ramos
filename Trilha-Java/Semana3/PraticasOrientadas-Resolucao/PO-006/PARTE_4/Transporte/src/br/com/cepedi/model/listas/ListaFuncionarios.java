package br.com.cepedi.model.listas;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.cepedi.exceptions.listaFuncionarios.FuncionarioJaCadastrado;
import br.com.cepedi.exceptions.listaFuncionarios.FuncionarioNaoEncontrado;
import br.com.cepedi.exceptions.listaVeiculos.VeiculoNaoEncontrado;
import br.com.cepedi.model.pessoa.Funcionario;
import br.com.cepedi.model.pessoa.Motorista;
import br.com.cepedi.model.pessoa.Passageiro;
import br.com.cepedi.model.pessoa.Pessoa;
import br.com.cepedi.model.veiculo.Veiculo;
import br.com.cepedi.persistenciaListas.Persistencia;

public class ListaFuncionarios extends ArrayList<Funcionario>  implements Serializable{
	
	public static final String CAMINHO_ARQUIVO = "./Dados/funcionarios.txt";
	private static final long serialVersionUID = 15L;

	public ListaFuncionarios() {
		super();
		carregarListaDoArquivo();
		corrigeGeracaoID();

	}

    private void carregarListaDoArquivo() {
    	ListaFuncionarios listaLida = Persistencia.lerDoArquivo(CAMINHO_ARQUIVO);
        if (listaLida != null) {
            this.addAll(listaLida);
        }
    }
    
    private void corrigeGeracaoID() {
    	for(Funcionario p : this) {
    		if(p.getId()>Pessoa.numeroDePessoas) {
    			Pessoa.numeroDePessoas=p.getId();
    		}
    	}    	
    }
	
	public void adiciona(Funcionario funcionario) throws FuncionarioJaCadastrado {
		
		if(funcionario==null) {
			throw new NullPointerException("Tentativa de inserir um valor nulo");
		}
		
		if(funcionarioJaCadastrado(funcionario.getCPF())) {
			throw new FuncionarioJaCadastrado();
		}

		add(funcionario);
			
	}

	public Funcionario buscar(String cpf) throws FuncionarioNaoEncontrado {
		
		if(cpf==null) {
			throw new NullPointerException("Foi inserido um cpf nulo na busca");
		}
		
		for(Funcionario f : this) {
			if(f.getCPF().replaceAll("[^0-9]", "").equals(cpf.replaceAll("[^0-9]", ""))) {
				return f;
			}
		}
		
		throw new FuncionarioNaoEncontrado();
	}
	
	
	public void deletar(String cpf) throws FuncionarioNaoEncontrado{
		
		if(cpf==null) {
			throw new NullPointerException("Foi inserido um cpf nulo na busca");
		}
		
		for(Funcionario f : this) {
			if(f.getCPF().replaceAll("[^0-9]", "").equals(cpf.replaceAll("[^0-9]", ""))) {
				this.remove(f);
				System.out.println("Funcionario excluido com sucesso!");
				return;
			}
		}
		throw new FuncionarioNaoEncontrado();
	}
	
	public void mostraTodos() {
		for(Funcionario p : this) {
			System.out.println(p);
		}
	}
	
	
	private boolean funcionarioJaCadastrado(String cpf) {
		for(Funcionario f : this) {
			if(f.getCPF().replaceAll("[^0-9]", "").equals(cpf.replaceAll("[^0-9]", ""))) {
				return true;
			}
		}
		return false;
	}
	

}
