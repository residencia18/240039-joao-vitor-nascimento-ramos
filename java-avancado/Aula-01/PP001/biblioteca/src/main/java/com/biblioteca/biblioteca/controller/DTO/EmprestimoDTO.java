package com.biblioteca.biblioteca.controller.DTO;

import java.time.LocalDateTime;

import com.biblioteca.biblioteca.model.Emprestimo;

public class EmprestimoDTO {
	
	private String nomeCliente;
	private String nomeLivro;
	private LocalDateTime data_emprestimo;
	private LocalDateTime data_devolucao;
	
	
  public EmprestimoDTO(Emprestimo emprestimo) {
        this.nomeCliente = emprestimo.getCliente().getNome();
        this.nomeLivro = emprestimo.getLivro().getNome();
        this.data_emprestimo = emprestimo.getData_emprestimo() != null ? emprestimo.getData_emprestimo() : LocalDateTime.now();
        this.data_devolucao = emprestimo.getData_devolucao() != null ? emprestimo.getData_devolucao() : null;
    }




	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public String getNomeLivro() {
		return nomeLivro;
	}


	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}


	public LocalDateTime getData_emprestimo() {
		return data_emprestimo;
	}


	public void setData_emprestimo(LocalDateTime data_emprestimo) {
		this.data_emprestimo = data_emprestimo;
	}


	public LocalDateTime getData_devolucao() {
		return data_devolucao;
	}


	public void setData_devolucao(LocalDateTime data_devolucao) {
		this.data_devolucao = data_devolucao;
	}
	
	
	
	

}
