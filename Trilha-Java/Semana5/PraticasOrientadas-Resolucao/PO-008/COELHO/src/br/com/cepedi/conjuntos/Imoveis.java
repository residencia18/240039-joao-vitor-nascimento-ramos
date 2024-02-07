package br.com.cepedi.conjuntos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import br.com.cepedi.exceptions.imovel.ImovelJaExistente;
import br.com.cepedi.exceptions.imovel.ImovelNaoEncontrado;
import br.com.cepedi.model.Cliente;
import br.com.cepedi.model.Endereco;
import br.com.cepedi.model.Imovel;

public class Imoveis extends HashSet<Imovel> {

	private static final long serialVersionUID = 1L;

	public Imoveis() {

	}

	public void adicionar(Imovel imovel) throws ImovelJaExistente {


		if (imovel == null) throw new IllegalArgumentException("tentativa de inserir valor nulo ou vazio");
		
		if(this.contains(imovel)) throw new ImovelJaExistente();

		add(imovel);

	}

	public Imovel buscar(int id) throws ImovelNaoEncontrado {
		
		if (id < 1) throw new IllegalArgumentException("Não existe id menor que 1");

	    return this.stream().filter(imovel -> imovel.getId()==id).findFirst().orElseThrow(ImovelNaoEncontrado::new);
	}
	
	public void atualizar(int id, String novaMatricula) throws ImovelNaoEncontrado  {

		Imovel imovel = buscar(id);

		imovel.setMatricula(novaMatricula);
		
	    System.out.println("Imóvel atualizado com sucesso!");
	}
	
	public void atualizar(int id,Endereco novoEndereco) throws ImovelNaoEncontrado  {

		Imovel imovel = buscar(id);


		imovel.setEndereco(novoEndereco);
		
	    System.out.println("Imóvel atualizado com sucesso!");
	}
	
	public void atualizar(int id, Cliente cliente) throws ImovelNaoEncontrado  {

		Imovel imovel = buscar(id);

		imovel.getProprietario().removeImovelNaLista(imovel);
        imovel.setProprietario(cliente);
        cliente.adicionaImovelNaLista(imovel);
	    System.out.println("Imóvel atualizado com sucesso!");
	}



	public void deletar(int id) throws ImovelNaoEncontrado {
		if (id < 1) throw new IllegalArgumentException("Não existe id menor que 1");
		
		Imovel imovel = buscar(id);
		
		this.remove(imovel);
		System.out.println("Imovel deletado com sucesso!");

	}

	public void listar() {
		
		if (this.isEmpty()) System.out.println("Não há imoveis cadastrados");


		imprimeConjunto(this);
	}

	public void listarOrdenadoPorID() throws Exception {
		
		if (this.isEmpty()) throw new Exception("Não há imoveis cadastrados");

		
		Set<Imovel> imoveisOrdenadosPorId = new TreeSet<>(Comparator.comparingInt(Imovel::getId));
		imoveisOrdenadosPorId.addAll(this);

		imprimeConjunto(imoveisOrdenadosPorId);
	}

	public void listarOrdenadoPorNome() throws Exception {
		
		if (this.isEmpty()) throw new Exception("Não há imoveis cadastrados");

		
		Set<Imovel> imoveisOrdenadosPorMatricula  = new TreeSet<>(Comparator.comparing(Imovel::getMatricula));
		imoveisOrdenadosPorMatricula.addAll(this);

		imprimeConjunto(imoveisOrdenadosPorMatricula);
	}

	private <T> void imprimeConjunto(Set<T> lista) {
	    lista.forEach(System.out::println);
	}
	
	
}
