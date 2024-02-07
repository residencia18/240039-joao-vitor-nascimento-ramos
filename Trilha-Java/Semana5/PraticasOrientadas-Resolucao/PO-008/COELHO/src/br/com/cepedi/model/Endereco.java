package br.com.cepedi.model;

import java.util.Objects;

import br.com.cepedi.verificacoes.cliente.VerificacoesEndereco;
import br.com.cepedi.verificacoes.geral.VerificacoesGeral;

public class Endereco implements Comparable<Endereco> {
	
	
	//-----ATRIBUTOS
	
	public static int qntIdsGerados=0;
	int id;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private Estado estado;
    private String cep;

	//-----CONSTRUTORES
    
    public Endereco() {
    	
    }
    
    public Endereco(String rua, int numero, String bairro, String cidade, Estado estado, String cep) {
        setRua(rua);
        setNumero(numero);
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
        setCEP(cep);
        qntIdsGerados++;
        this.id = qntIdsGerados;
    }
    
    public Endereco(int id ,String rua, int numero, String bairro, String cidade, Estado estado, String cep) {
        setRua(rua);
        setNumero(numero);
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
        setCEP(cep);
        this.id = id;
		corrigeGeradorID(id);
	}

	private void corrigeGeradorID(int id) {
		if(id > qntIdsGerados) {
			qntIdsGerados =id;
		}
	}


	//-----GETTERS AND SETTERS

    
    public String getRua() {
        return rua;
    }

    public int getId() {
		return id;
	}
    

	public void setId(int id) {
		VerificacoesGeral.verificaID(id);	
		this.id = id;
	}
	


	public void setRua(String rua) {
		
		VerificacoesGeral.verificaStringVaziaOuNula(rua);

        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
    	
    	if(numero<0) {
			throw new IllegalArgumentException("não existem casas com números negativos");
    	}else if(numero==0) {
			throw new IllegalArgumentException("não existem casas com número zero");
    	}
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
		VerificacoesGeral.verificaStringVaziaOuNula(bairro);
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
		VerificacoesGeral.verificaStringVaziaOuNula(cidade);

        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        if (estado == null) {
            throw new IllegalArgumentException("Tentativa de inserir valor nulo para o estado");
        }
        this.estado = estado;
    }

    public String getCEP() {
        return cep;
    }

    public void setCEP(String cep) {
		if(cep==null || cep.isEmpty()) {
			throw new IllegalArgumentException("tentativa de inserir valor nulo ou vazio");
		}else if(!VerificacoesEndereco.verificaCEP(cep)) {
			throw new IllegalArgumentException("CEP inválido");
		}
		
        this.cep = cep;
    }

	//-----TO STRING


	@Override
	public String toString() {
		return "Endereco [id=" + id + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cidade="
				+ cidade + ", estado=" + estado + ", cep=" + cep + "]";
	}



	//-----EQUALS AND HASHCODE

	@Override
	public int hashCode() {
		return Objects.hash(bairro, cep, cidade, estado, numero, rua);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cep, other.cep)
				&& Objects.equals(cidade, other.cidade) && estado == other.estado && numero == other.numero
				&& Objects.equals(rua, other.rua);
	}


	//------COMPARE TO

	@Override
	public int compareTo(Endereco o) {
		return Integer.compare(this.id, o.getId());
	}


    
}
