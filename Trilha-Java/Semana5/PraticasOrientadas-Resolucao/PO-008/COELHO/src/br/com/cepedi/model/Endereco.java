package br.com.cepedi.model;

import br.com.cepedi.verificacoes.cliente.VerificacoesEndereco;

public class Endereco {
	
	int id;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private Estado estado;
    private String cep;

    public Endereco(String rua, int numero, String bairro, String cidade, Estado estado, String cep) {
        setRua(rua);
        setNumero(numero);
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
        setCEP(cep);
    }


    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
		if(rua==null || rua.isEmpty()) {
			throw new IllegalArgumentException("tentativa de inserir valor nulo ou vazio");
		}
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
		if(bairro==null || bairro.isEmpty()) {
			throw new IllegalArgumentException("tentativa de inserir valor nulo ou vazio");
		}
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
		if(cidade==null || cidade.isEmpty()) {
			throw new IllegalArgumentException("tentativa de inserir valor nulo ou vazio");
		}
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

}
